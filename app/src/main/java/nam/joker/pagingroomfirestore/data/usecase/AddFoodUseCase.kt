package nam.joker.pagingroomfirestore.data.usecase

import nam.joker.pagingroomfirestore.data.repo.FoodRepository

interface AddFoodUseCase {
    var repo: FoodRepository
}
