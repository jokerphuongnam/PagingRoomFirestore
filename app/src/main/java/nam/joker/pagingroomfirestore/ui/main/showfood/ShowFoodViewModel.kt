package nam.joker.pagingroomfirestore.ui.main.showfood

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagedList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import nam.joker.pagingroomfirestore.data.database.domain.Food
import nam.joker.pagingroomfirestore.data.usecase.ShowFoodUseCase

@InternalCoroutinesApi
class ShowFoodViewModel(private val useCase: ShowFoodUseCase) : ViewModel() {

    val liveDataListFood by lazy {
//        MutableLiveData<PagedList<Food>?>().also {
//            viewModelScope.launch(Dispatchers.IO) {
//                useCase.getOnePage().collect {data->
//                    it.postValue(data)
//                }
//            }
//        }
        useCase.getOnePage().asLiveData(Dispatchers.IO)
    }
    val isLoading: LiveData<Boolean> by lazy {
        Transformations.map(liveDataListFood) { data ->
            return@map data == null || data.size == 0
        }
    }

    fun refresh() = useCase.refresh()
}