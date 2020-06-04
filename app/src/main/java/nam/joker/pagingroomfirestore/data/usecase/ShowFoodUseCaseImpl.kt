package nam.joker.pagingroomfirestore.data.usecase

import nam.joker.pagingroomfirestore.data.repo.FoodRepository

class ShowFoodUseCaseImpl(override var repo: FoodRepository) : ShowFoodUseCase {
    override fun check() {
        repo.check()
    }

    override fun getOnePage() = repo.getOnePage()
    override fun refresh() = repo.refresh()

}