package nam.joker.pagingroomfirestore.data.database.network

import nam.joker.pagingroomfirestore.data.database.domain.Food

interface NetworkConnect {
    fun check()
    fun refresh()
    fun getOnePage(onSuccess: (List<Food>)-> Unit)
    fun insertFood(food: Food)
}