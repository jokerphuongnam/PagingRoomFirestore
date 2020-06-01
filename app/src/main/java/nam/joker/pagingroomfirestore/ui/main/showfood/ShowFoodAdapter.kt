package nam.joker.pagingroomfirestore.ui.main.showfood

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nam.joker.pagingroomfirestore.data.database.domain.Food
import nam.joker.pagingroomfirestore.databinding.ItemShowFoodBinding

class ShowFoodAdapter : PagedListAdapter<Food, ShowFoodAdapter.FoodViewHolder>(diff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder = FoodViewHolder.create(parent, viewType)

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) = holder.bind(getItem(position))

    class FoodViewHolder(private val itemBinding: ItemShowFoodBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Food?) {

        }

        companion object{
            fun create(parent: ViewGroup, viewType: Int): FoodViewHolder = FoodViewHolder(
                ItemShowFoodBinding.inflate(LayoutInflater.from(parent.context), parent,false))
        }
    }

    companion object{
        val diff = object :DiffUtil.ItemCallback<Food>(){
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean = oldItem == newItem
        }
    }
}