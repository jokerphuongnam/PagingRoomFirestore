package nam.joker.pagingroomfirestore.data.database

import androidx.paging.PagedList
import nam.joker.pagingroomfirestore.data.database.domain.Food

/**
 * tạo class liên kết giữa online paging và offline paging
 * */
class DataCallback(private val onDownload: ()-> Unit): PagedList.BoundaryCallback<Food>() {
    /**
     * khi không có phần từ sẽ gọi hàm này
     * */
    override fun onZeroItemsLoaded() {
        onDownload()
    }

    /**
     * khi có hết phần tử của 1 trang sẽ gọi hàm này
     * */
    override fun onItemAtEndLoaded(itemAtEnd: Food) {
        onDownload()
    }

    override fun onItemAtFrontLoaded(itemAtFront: Food) {

    }
}