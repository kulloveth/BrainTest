package kulloveth.developer.com.braintest.ui.auth


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_email_sign_in.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.repository.UserRepository
import kulloveth.developer.com.braintest.databinding.FragmentEmailSignInBinding
import kulloveth.developer.com.braintest.utils.signIntoGameFragment

/**
 * A simple [Fragment] subclass.
 */
class EmailSignInFragment : Fragment(), AuthListener {

    private lateinit var factory: AuthViewModelFactory
    var repository = UserRepository()
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: FragmentEmailSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        factory = AuthViewModelFactory(repository)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_email_sign_in, container, false)
        viewModel = ViewModelProvider(requireActivity(), factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
        return binding.root
    }


    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
        view?.signIntoGameFragment()
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

    override fun onStart() {
        super.onStart()
        viewModel.user?.let {
            view?.signIntoGameFragment()
        }
    }
}
