package nam.joker.pagingroomfirestore.ui.main.showfood

import androidx.lifecycle.ViewModel
import nam.joker.pagingroomfirestore.data.usecase.ShowFoodUseCase

class ShowFoodViewModel(private val useCase: ShowFoodUseCase) : ViewModel() {
    init {
        useCase.check()
    }
}