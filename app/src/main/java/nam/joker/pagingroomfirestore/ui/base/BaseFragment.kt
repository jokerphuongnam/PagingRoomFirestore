package nam.joker.pagingroomfirestore.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
        onViewCreated()

    @Suppress("UNCHECKED_CAST")
    private fun viewModelClass(): KClass<VM> =
        // dirty hack to get generic type https://stackoverflow.com/a/1901275/719212
        ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>).kotlin

}