package nam.joker.pagingroomfirestore.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import kotlinx.coroutines.flow.Flow
import nam.joker.pagingroomfirestore.data.database.domain.Food
import nam.joker.pagingroomfirestore.data.database.local.LocalConnect
import nam.joker.pagingroomfirestore.data.database.network.NetworkConnect

interface FoodRepository {
    var local: LocalConnect
    var network: NetworkConnect
    fun check()
    //fun getOnePage(): Flow<PagedList<Food>?>
    fun getOnePage(): LiveData<PagedList<Food>?>
    fun refresh()
}