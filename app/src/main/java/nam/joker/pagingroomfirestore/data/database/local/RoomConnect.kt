package nam.joker.pagingroomfirestore.data.database.local

import android.content.Context
import android.util.Log
import androidx.paging.DataSource
import androidx.room.*
import nam.joker.pagingroomfirestore.data.database.domain.Food
import nam.joker.pagingroomfirestore.data.database.local.LocalConnect.Companion.DB_VERSION

@Database(entities = [Food::class], version = DB_VERSION)
abstract class RoomConnect : RoomDatabase(), LocalConnect {
    override fun check() {
        Log.d("bbbbbbbbbbbbbbbbbbbbb", "local connect")
    }

    /**
     * getFoodDao sẽ được implement bằng room
     * */
    protected abstract fun getFoodDao(): FoodDao

    override fun getPageOne(): DataSource.Factory<Int, Food> = getFoodDao().getPageOne()

    override suspend fun insertFood(vararg item: Food) = getFoodDao().insertFood(*item)

    override suspend fun deleteAllFood() = getFoodDao().deleteAllFood()


    /**
     *tạo interface *Dao để room tự implement các query, insert, delete, update
     * */
    @Dao
    interface FoodDao {
        @Query("SELECT *FROM FOODS")
        fun getPageOne(): DataSource.Factory<Int, Food>

        @Insert
        suspend fun insertFood(vararg item: Food)

        @Query("DELETE FROM FOODS")
        suspend fun deleteAllFood()
    }
    companion object {
        private const val DB_NAME = "PagingDB.db"
        /**
         * tạo singletone để hoạt động cho hàm abstract getFoodDao
         * getInstance sẽ gán biến intance khi instance là null nếu instance khác null thì return luôn biến intance
         * */
        @Volatile
        private var instance: RoomConnect? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: build(context).also { instance = it }
        }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, RoomConnect::class.java, DB_NAME)
                .build()
    }
}