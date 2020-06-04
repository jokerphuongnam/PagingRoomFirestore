package nam.joker.pagingroomfirestore.di

import nam.joker.pagingroomfirestore.data.database.domain.Food
import nam.joker.pagingroomfirestore.data.database.local.LocalConnect
import nam.joker.pagingroomfirestore.data.database.local.RoomConnect
import nam.joker.pagingroomfirestore.data.database.network.FirebaseConnect
import nam.joker.pagingroomfirestore.data.database.network.NetworkConnect
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseDi = module {
    single<LocalConnect> { RoomConnect.getInstance(androidContext()) }
    single<NetworkConnect> { FirebaseConnect() }
    factory { Food() }
}