package nam.joker.pagingroomfirestore.di

import kotlinx.coroutines.InternalCoroutinesApi
import nam.joker.pagingroomfirestore.ui.main.showfood.ShowFoodAdapter
import nam.joker.pagingroomfirestore.ui.main.showfood.ShowFoodViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@InternalCoroutinesApi
val showFoodUiDi = module {
    viewModel { ShowFoodViewModel(get()) }
    factory { ShowFoodAdapter() }
}