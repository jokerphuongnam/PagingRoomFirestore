package nam.joker.pagingroomfirestore.data.repo

import nam.joker.pagingroomfirestore.data.database.local.LocalConnect
import nam.joker.pagingroomfirestore.data.database.network.NetworkConnect

class FoodRepositoryImpl(override var local: LocalConnect, override var network: NetworkConnect) : FoodRepository {
    override fun check() {
        local.check()
        network.check()
    }
}