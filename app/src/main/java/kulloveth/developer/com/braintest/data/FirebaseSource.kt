package kulloveth.developer.com.braintest.data

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable

class FirebaseSource {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun signIn(email: String, password: String) =
        Completable.create { notify ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!notify.isDisposed) {
                        if (it.isSuccessful) {
                            notify.onComplete()
                        } else {
                            notify.onError(it.exception!!)
                        }
                    }
                }
        }

    fun signUp(email: String, password: String) = Completable.create { notify ->
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!notify.isDisposed) {
                if (it.isSuccessful) {
                    notify.onComplete()
                } else {
                    notify.onError(it.exception!!)
                }
            }
        }
    }

    fun signOut() = firebaseAuth.signOut()

    fun currenUser() = firebaseAuth.currentUser
}