package nam.joker.pagingroomfirestore.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import nam.joker.pagingroomfirestore.data.repo.FoodRepository
import nam.joker.pagingroomfirestore.data.repo.FoodRepositoryImpl
import org.koin.dsl.module

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
val repositoryDi = module {
    single<FoodRepository> {FoodRepositoryImpl(get(), get())}
}