package kulloveth.developer.com.braintest.data.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kulloveth.developer.com.braintest.data.RetrofitService
import kulloveth.developer.com.braintest.data.models.Quiz
import retrofit2.Response

class GameRepository {



//
//    class QuizDatabaseCallback : RoomDatabase.Callback() {
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//
//            QuizDatabase.INSTANCE.let { database ->
//                populateDatabase(database!!.quizDao())
//                    .subscribeOn(Schedulers.io())
//
//
//            }
//        }
//
//        fun populateDatabase(quizDao: QuizDao) = Completable.create { notify ->
//            quizDao.deleteAllQuiz()
//            GameRepository().quizLiveData.observe(this , Observer{
//
//            })
//            quizDao.insertQuiz(Quiz(UUID.randomUUID().toString(),GameRepository().quizLiveData.))
//
//        }
    // }
}