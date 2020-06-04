package nam.joker.pagingroomfirestore.data.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import kotlinx.coroutines.flow.Flow
import nam.joker.pagingroomfirestore.data.database.domain.Food
import nam.joker.pagingroomfirestore.data.repo.FoodRepository

interface ShowFoodUseCase {
    var repo: FoodRepository
    fun check()
    //fun getOnePage(): Flow<PagedList<Food>?>
    fun getOnePage(): LiveData<PagedList<Food>?>
    fun refresh()
}