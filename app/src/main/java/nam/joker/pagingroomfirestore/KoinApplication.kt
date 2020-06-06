package nam.joker.pagingroomfirestore

import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import nam.joker.pagingroomfirestore.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApplication: Application() {
    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinApplication)
            modules(databaseDi, repositoryDi, useCaseDi)
            modules(showFoodUiDi, addFoodUiDi)
        }
    }
}