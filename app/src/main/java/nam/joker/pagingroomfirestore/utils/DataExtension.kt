package nam.joker.pagingroomfirestore.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * chưa đủ kiến thức
 * */
@ExperimentalCoroutinesApi
suspend fun <T> LiveData<T?>.toFlow() = channelFlow {
    send(value)
    val observer = Observer<T?> { data ->
        offer(data)
    }
    withContext(Dispatchers.Main.immediate){
        observeForever(observer)
    }
    invokeOnClose {
        removeObserver(observer)
    }
}