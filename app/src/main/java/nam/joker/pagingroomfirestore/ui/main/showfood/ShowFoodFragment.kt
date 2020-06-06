package nam.joker.pagingroomfirestore.ui.main.showfood

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.InternalCoroutinesApi
import nam.joker.pagingroomfirestore.R
import nam.joker.pagingroomfirestore.databinding.FragmentShowFoodBinding
import nam.joker.pagingroomfirestore.ui.base.BaseFragment
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
@InternalCoroutinesApi
class ShowFoodFragment : BaseFragment<ShowFoodViewModel, FragmentShowFoodBinding>() {
    private val showFoodAdapter by inject<ShowFoodAdapter>()

    override fun getLayout(): Int = R.layout.fragment_show_food

    override fun onViewCreated() {
        with(baseBinding.showFoodRecycler){
            adapter = showFoodAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        observerData(baseViewmodel.liveDataListFood, Observer { listFood ->
            showFoodAdapter.submitList(listFood)
        })
        with(baseBinding.refreshShowfood) {
            observerData(baseViewmodel.isLoading, Observer { isLoad ->
                isRefreshing = isLoad
            })
            setOnRefreshListener { baseViewmodel.refresh() }
        }
    }
}