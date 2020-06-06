package nam.joker.pagingroomfirestore.di

import nam.joker.pagingroomfirestore.data.usecase.AddFoodUseCase
import nam.joker.pagingroomfirestore.data.usecase.AddFoodUseCaseImpl
import nam.joker.pagingroomfirestore.data.usecase.ShowFoodUseCase
import nam.joker.pagingroomfirestore.data.usecase.ShowFoodUseCaseImpl
import org.koin.dsl.module

val useCaseDi = module {
    single<ShowFoodUseCase> { ShowFoodUseCaseImpl(get()) }
    single<AddFoodUseCase> { AddFoodUseCaseImpl(get()) }
}