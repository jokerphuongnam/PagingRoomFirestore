package nam.joker.pagingroomfirestore.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.*
import nam.joker.pagingroomfirestore.data.database.DataCallback
import nam.joker.pagingroomfirestore.data.database.domain.Food
import nam.joker.pagingroomfirestore.data.database.local.LocalConnect
import nam.joker.pagingroomfirestore.data.database.network.NetworkConnect

@Suppress("UNCHECKED_CAST")
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class FoodRepositoryImpl(override var local: LocalConnect, override var network: NetworkConnect) : FoodRepository {
    override fun check() {
        local.check()
        network.check()
    }

    override fun getOnePage(): LiveData<PagedList<Food>?> {
        /**
        * biến cài đặt một vài tính năng của list
        * setInitialLoadSizeHint: set số tối đa lượng phần tử ở lần load đầu
        * setPageSize: set tối đa số lượng phần tử ở những lần load sau
        * setPrefetchDistance: cài đặt khi đến phần tử số bao nhiêu sẽ load
        * */
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(40)
            .setPageSize(20)
            .setPrefetchDistance(10)
            .build()
        return LivePagedListBuilder(
            local.getPageOne(),
            config
        ).setBoundaryCallback(DataCallback {
            network.getOnePage { listFood ->
                GlobalScope.launch(Dispatchers.IO) {
                    local.insertFood(*listFood.toTypedArray())
                }
            }
        }).build()
    }

    /**
     * tạo bộ cài đặt, cài đặt cho LivePagedListBuilder để trả về LiveData và ép kiểu về Flow (LiveData nằm ở ViewModel)
     * chưa đủ kiến thức
     * */
//    override fun getOnePage(): Flow<PagedList<Food>?> = flowData
//
//    private var flowData: Flow<PagedList<Food>?> = object : Flow<PagedList<Food>?> {
//        @InternalCoroutinesApi
//        override suspend fun collect(collector: FlowCollector<PagedList<Food>?>) {
//            /**
//             * biến cài đặt một vài tính năng của list
//             * setInitialLoadSizeHint: set số tối đa lượng phần tử ở lần load đầu
//             * setPageSize: set tối đa số lượng phần tử ở những lần load sau
//             * setPrefetchDistance: cài đặt khi đến phần tử số bao nhiêu sẽ load
//             * */
//            val config = PagedList.Config.Builder()
//                .setInitialLoadSizeHint(40)
//                .setPageSize(20)
//                .setPrefetchDistance(10)
//                .build()
//            LivePagedListBuilder(
//                local.getPageOne(),
//                config
//            ).setBoundaryCallback(DataCallback {
//                network.getOnePage { listFood ->
//                    GlobalScope.launch(Dispatchers.IO) {
//                        local.insertFood(*listFood.toTypedArray())
//                    }
//                }
//            }).build().toFlow().collect(collector)
//        }
//    }

    /**
     * refresh network và xoá toàn bộ dữ liệu trên local (xoá cache)
     * */
    override fun refresh() {
        GlobalScope.launch(Dispatchers.IO) {
            network.refresh()
            local.deleteAllFood()
        }
    }
}