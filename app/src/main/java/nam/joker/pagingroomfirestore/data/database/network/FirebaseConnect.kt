package nam.joker.pagingroomfirestore.data.database.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import nam.joker.pagingroomfirestore.data.database.domain.Food

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FirebaseConnect : NetworkConnect {
    private val db = FirebaseFirestore.getInstance()

    init {
        db.firestoreSettings =
            FirebaseFirestoreSettings.Builder().setPersistenceEnabled(false).build()
    }

    override fun check() {
        Log.d("ccccccccccccccccccccccccccccccccccc", "network check")
    }


    /**
     * tạo 2 biến, first là phần tử đầu tiên của page khi lấy về
     * next nếu là null thì là trang đầu tiên vì sau khi getOnePage được chạy next sẽ luôn được gán
     * */
    private val first = db.collection(Food.NAME_MODEL).limit(40)
    private var next: Query? = null

    /**
     * gán next = null để đánh lừa các hàm sau và lấy page đầu tiên
     */
    override fun refresh() {
        next = null
    }

    /**
    * lấy một page sẽ kiểm tra next -> next == null là chỉ có trang đầu tiên, next != null không phải trang đầu tiên
    * gán next một phần của trang
    * bắn ngược lên bằng hàm
    * */
    override fun getOnePage(onSuccess: (List<Food>) -> Unit) {
        if (next != null) {
            next!!.get().addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {
                    next = db.collection(Food.NAME_MODEL)
                        .startAfter(querySnapshot.documents[querySnapshot.size() - 1]).limit(20)
                    onSuccess(querySnapshot.map { it.toObject(Food::class.java) })
                }
            }
        } else {
            first.get().addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {
                    next = db.collection(Food.NAME_MODEL)
                        .startAfter(querySnapshot.documents[querySnapshot.size() - 1]).limit(40)
                    onSuccess(querySnapshot.map { it.toObject(Food::class.java) })
                }
            }
        }
    }

    override fun insertFood(food: Food) {
        db.collection(Food.NAME_MODEL).add(food).addOnSuccessListener {
            Log.d("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", food.name)
        }.addOnFailureListener{exception ->
            Log.d("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", exception.message)
        }.addOnCompleteListener{
            Log.d("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", food.name)
        }.addOnCanceledListener {
            Log.d("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", food.name)
        }
    }
}