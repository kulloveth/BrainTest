package kulloveth.developer.com.braintest.data

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import kulloveth.developer.com.braintest.R

class FirebaseSource {


//    private   val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//        .requestIdToken(getString(R.string.default_web_client_id))
//        .requestEmail()
//        .build()


    companion object {
        private var firebaseSource: FirebaseSource? = null
        private var firebaseAuth: FirebaseAuth? = null
        fun initializeFirebase() {
            if (firebaseSource == null) {
                firebaseSource = FirebaseSource()
                firebaseAuth = FirebaseAuth.getInstance()
            }
        }

        fun initializeGoogleSignin(context: Context) = Completable.create { notify ->
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        }

        fun signIn(email: String, password: String) =
            Completable.create { notify ->
                firebaseAuth?.signInWithEmailAndPassword(email, password)!!.addOnCompleteListener {
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
            firebaseAuth?.createUserWithEmailAndPassword(email, password)!!.addOnCompleteListener {
                if (!notify.isDisposed) {
                    if (it.isSuccessful) {
                        notify.onComplete()
                    } else {
                        notify.onError(it.exception!!)
                    }
                }
            }
        }

        fun googleSignup() = Completable.create { notify ->

        }

        fun signOut() = firebaseAuth!!.signOut()

        fun currenUser() = firebaseAuth!!.currentUser
    }


}