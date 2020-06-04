package nam.joker.pagingroomfirestore.ui.main.showfood

import androidx.lifecycle.*
import androidx.paging.PagedList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import nam.joker.pagingroomfirestore.data.database.domain.Food
import nam.joker.pagingroomfirestore.data.usecase.ShowFoodUseCase

class ShowFoodViewModel(private val useCase: ShowFoodUseCase) : ViewModel() {
    fun check() {
        useCase.check()
    }

    val liveDataListFood by lazy {
//        MutableLiveData<PagedList<Food>?>().also {
//            viewModelScope.launch(Dispatchers.IO) {
//                useCase.getOnePage().collect { data ->
//                    it.postValue(data)
//                }
//            }
//        }
        useCase.getOnePage()
    }
    val isLoading: LiveData<Boolean> by lazy {
        Transformations.map(liveDataListFood) { data ->
            return@map data == null || data.size == 0
        }
    }

    fun refresh() = useCase.refresh()
}