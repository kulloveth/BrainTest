package kulloveth.developer.com.braintest.ui


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.fragment_choose_sign_in_method.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.FirebaseSource
import kulloveth.developer.com.braintest.data.FirebaseSource.Companion.RC_SIGN_IN
import kulloveth.developer.com.braintest.data.repository.UserRepository
import kulloveth.developer.com.braintest.ui.auth.AuthListener
import kulloveth.developer.com.braintest.ui.auth.AuthViewModel
import kulloveth.developer.com.braintest.ui.auth.AuthViewModelFactory
import kulloveth.developer.com.braintest.utils.signIntoGameFragment

/**
 * A simple [Fragment] subclass.
 */
class ChooseSignInMethodFragment : Fragment(), AuthListener {


    private lateinit var factory: AuthViewModelFactory
    var repository = UserRepository()
    private lateinit var viewModel: AuthViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_choose_sign_in_method, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factory = AuthViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(AuthViewModel::class.java)
        emailOption.setOnClickListener {
            it.findNavController().navigate(R.id.action_chooseSignInMethodFragment_to_emailSignIn)
        }

        gmailOption.setOnClickListener {
            //            Snackbar.make(it, "Google Option is currently unavailable", Snackbar.LENGTH_SHORT)
//                .show()

            signIn()

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                account?.let {
                    viewModel.sginwithGmail(account)

                    view?.findNavController()?.navigate(R.id.action_chooseSignInMethodFragment_to_gameFragment)

                }

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun signIn() {
        val signInIntent = FirebaseSource.mClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    companion object {
        var TAG = "ChooseSignInMethodFragment"
    }

    override fun onResume() {
        super.onResume()
        FirebaseSource.initializeGoogleSignin(requireContext())
    }

    override fun onStarted() {

    }

    override fun onSuccess() {
    }

    override fun onFailure(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onStart() {
        super.onStart()
        viewModel.user?.let {
            view?.findNavController()?.navigate(R.id.action_chooseSignInMethodFragment_to_gameFragment)
        }
    }
}
