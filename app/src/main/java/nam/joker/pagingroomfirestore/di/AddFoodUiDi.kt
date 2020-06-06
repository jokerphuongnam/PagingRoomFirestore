package nam.joker.pagingroomfirestore.di

import nam.joker.pagingroomfirestore.ui.main.addfood.AddFoodViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addFoodUiDi = module {
    viewModel {AddFoodViewModel(get())}
}