package nam.joker.pagingroomfirestore.ui.main.showfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import nam.joker.pagingroomfirestore.R
import nam.joker.pagingroomfirestore.databinding.FragmentShowFoodBinding
import nam.joker.pagingroomfirestore.ui.base.BaseFragment
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class ShowFoodFragment : BaseFragment<ShowFoodViewModel, FragmentShowFoodBinding>() {
    private val showFoodAdapter by inject<ShowFoodAdapter>()

    override fun getLayout(): Int = R.layout.fragment_show_food

    override fun onViewCreated() {
        with(baseBinding.showFoodRecycler){
            adapter = showFoodAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}