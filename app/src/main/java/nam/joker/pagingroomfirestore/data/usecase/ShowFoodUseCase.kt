package nam.joker.pagingroomfirestore.data.usecase

import nam.joker.pagingroomfirestore.data.repo.FoodRepository

interface ShowFoodUseCase {
    var repo: FoodRepository
    fun check()
}