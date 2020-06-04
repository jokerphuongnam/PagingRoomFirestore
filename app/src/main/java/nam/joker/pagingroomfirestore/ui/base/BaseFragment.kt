package nam.joker.pagingroomfirestore.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import es.dmoral.toasty.Toasty
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseFragment<VM : ViewModel, VB : ViewDataBinding> : Fragment() {
    @LayoutRes
    protected abstract fun getLayout(): Int
    protected val baseViewmodel: VM by viewModel(viewModelClass())
    protected lateinit var baseBinding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        return baseBinding.root
    }

    protected abstract fun onViewCreated()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewCreated()
    }

    protected fun successToasty(successMessenger: String, duration: Int = Toasty.LENGTH_SHORT) =
        Toasty.success(requireContext(), successMessenger, duration).show()

    protected fun <T, LD : LiveData<T>> observerData(data: LD, observer: Observer<T>) =
        data.observe(viewLifecycleOwner, observer)

    @Suppress("UNCHECKED_CAST")
    private fun viewModelClass(): KClass<VM> =
        // dirty hack to get generic type https://stackoverflow.com/a/1901275/719212
        ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>).kotlin

}