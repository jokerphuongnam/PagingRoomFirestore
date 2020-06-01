package nam.joker.pagingroomfirestore

import android.app.Application
import nam.joker.pagingroomfirestore.di.databaseDi
import nam.joker.pagingroomfirestore.di.repositoryDi
import nam.joker.pagingroomfirestore.di.showFoodUiDi
import nam.joker.pagingroomfirestore.di.useCaseDi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinApplication)
            modules(databaseDi, repositoryDi, useCaseDi)
            modules(showFoodUiDi)
        }
    }
}