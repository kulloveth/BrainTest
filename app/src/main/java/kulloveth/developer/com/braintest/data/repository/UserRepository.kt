package kulloveth.developer.com.braintest.data.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kulloveth.developer.com.braintest.data.FirebaseSource
import kulloveth.developer.com.braintest.data.RetrofitService
import kulloveth.developer.com.braintest.data.models.Quiz
import retrofit2.Response

class UserRepository {

    fun signInUser(email: String, password: String) = FirebaseSource.signIn(email, password)

    fun signupUser(email: String, password: String) = FirebaseSource.signUp(email, password)



    fun signOutUser() = FirebaseSource.signOut()

    fun currentUser() = FirebaseSource.currenUser()

    fun signInwithGmail(
        googleSignInAccount: GoogleSignInAccount
    ) = FirebaseSource.firebaseAuthWithGoogle(googleSignInAccount)


    val quizLiveData: MutableLiveData<Quiz> by lazy {
        MutableLiveData<Quiz>().also {
            fetchQuiz()
        }
    }

    fun fetchQuiz() {
        val api = RetrofitService.getRetrofitInstance().fetchQuiz()
        api.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<Quiz>> {
                override fun onSuccess(t: Response<Quiz>) {
                    quizLiveData.value = t.body()
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }

            })
    }
}