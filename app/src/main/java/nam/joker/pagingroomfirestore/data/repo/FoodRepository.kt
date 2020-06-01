package nam.joker.pagingroomfirestore.data.repo

import nam.joker.pagingroomfirestore.data.database.local.LocalConnect
import nam.joker.pagingroomfirestore.data.database.network.NetworkConnect

interface FoodRepository {
    var local: LocalConnect
    var network: NetworkConnect
    fun check()
}