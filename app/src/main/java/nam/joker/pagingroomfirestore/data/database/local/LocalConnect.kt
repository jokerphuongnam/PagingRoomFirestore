package nam.joker.pagingroomfirestore.data.database.local

import androidx.paging.DataSource
import nam.joker.pagingroomfirestore.data.database.domain.Food

interface LocalConnect {
    fun getPageOne(): DataSource.Factory<Int, Food>
    suspend fun insertFood(vararg item: Food)
    suspend fun deleteAllFood()
    companion object{
        const val DB_VERSION = 1
    }
}