package kulloveth.developer.com.braintest.data

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import io.reactivex.Completable
import kulloveth.developer.com.braintest.MainActivity
import kulloveth.developer.com.braintest.R
import java.lang.ref.WeakReference

class FirebaseSource {


//    private   val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//        .requestIdToken(getString(R.string.default_web_client_id))
//        .requestEmail()
//        .build()


    companion object {
        private var firebaseSource: FirebaseSource? = null
        private var firebaseAuth: FirebaseAuth? = null
        private var mClient: WeakReference<GoogleSignInClient>? = null
        fun initializeFirebase() {
            if (firebaseSource == null) {
                firebaseSource = FirebaseSource()
                firebaseAuth = FirebaseAuth.getInstance()
            }
        }

        fun initializeGoogleSignin(context: Context) {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            mClient = WeakReference(GoogleSignIn.getClient(context, gso))
        }

        fun getGoogleClient(): GoogleSignInClient? {
            return mClient?.get()
        }

        fun firebaseAuthWithGoogle(
            activity: MainActivity,
            googleSignInAccount: GoogleSignInAccount
        ) = Completable.create { notify ->
            val authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
            firebaseAuth?.signInWithCredential(authCredential)?.addOnCompleteListener {
                if (!notify.isDisposed) {
                    if (it.isSuccessful) {
                        notify.onComplete()
                    } else {
                        notify.onError(it.exception!!)
                    }
                }
            }

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

        fun signOut() {
            firebaseAuth?.signOut()
            getGoogleClient()?.signOut()

            fun currenUser() = firebaseAuth!!.currentUser
        }


    }