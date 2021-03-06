package kulloveth.developer.com.braintest.ui.auth


import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.repository.UserRepository

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null
    private val disposables = CompositeDisposable()

    val user by lazy {
        repository.currentUser()
    }

    fun signIn() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }
        authListener?.onStarted()
        val disposable = repository.signInUser(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)


    }

    fun signup() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Please input all values")
            return
        }
        authListener?.onStarted()
        val disposable = repository.signupUser(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    fun sginwithGmail(
        googleSignInAccount: GoogleSignInAccount
    ) {
        authListener?.onStarted()
        val disposable = repository.signInwithGmail(
            googleSignInAccount
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ authListener?.onSuccess() }, {
                authListener?.onFailure(it.message!!)

            })
        disposables.add(disposable)
    }

    fun goToSignup(view: View) {
        view.findNavController().navigate(R.id.action_emailSignIn_to_emailSignUp)
    }


    fun goToSignIn(view: View) {
        view.findNavController().navigate(R.id.action_emailSignUp_to_emailSignIn)
    }


    fun signOut() = repository.signOutUser()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}