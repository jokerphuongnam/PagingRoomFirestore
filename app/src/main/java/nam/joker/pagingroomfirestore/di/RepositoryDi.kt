package nam.joker.pagingroomfirestore.di

import nam.joker.pagingroomfirestore.data.repo.FoodRepository
import nam.joker.pagingroomfirestore.data.repo.FoodRepositoryImpl
import org.koin.dsl.module

val repositoryDi = module {
    single<FoodRepository> {FoodRepositoryImpl(get(), get())}
}