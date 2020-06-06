package nam.joker.pagingroomfirestore.data.usecase

import nam.joker.pagingroomfirestore.data.repo.FoodRepository

class AddFoodUseCaseImpl(override var repo: FoodRepository) : AddFoodUseCase