package kulloveth.developer.com.braintest.ui.auth


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_email_sign_up.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.FirebaseSource
import kulloveth.developer.com.braintest.data.repository.UserRepository
import kulloveth.developer.com.braintest.databinding.FragmentEmailSignUpBinding
import kulloveth.developer.com.braintest.ui.game.GameFragment
import kulloveth.developer.com.braintest.utils.startGameFragment

/**
 * A simple [Fragment] subclass.
 */
class EmailSignUpFragment : Fragment(), AuthListener {

    private lateinit var factory: AuthViewModelFactory
    var firebaseSource = FirebaseSource()
    var repository = UserRepository(firebaseSource)
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: FragmentEmailSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        factory = AuthViewModelFactory(repository)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_email_sign_up, container, false)
        viewModel = ViewModelProvider(requireActivity(), factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
        return binding.root
    }


    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
        Intent(requireActivity(), GameFragment::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
       view?.findNavController()!!.navigate(R.id.action_emailSignUp_to_gameFragment)
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

}
