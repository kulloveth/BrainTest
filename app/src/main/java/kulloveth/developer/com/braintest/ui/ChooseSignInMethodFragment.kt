package kulloveth.developer.com.braintest.ui


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_choose_sign_in_method.*

import kulloveth.developer.com.braintest.R

/**
 * A simple [Fragment] subclass.
 */
class ChooseSignInMethodFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_sign_in_method, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailOption.setOnClickListener {
            it.findNavController().navigate(R.id.action_chooseSignInMethodFragment_to_emailSignIn)
        }

        gmailOption.setOnClickListener {
            Snackbar.make(it, "Google Option is currently unavailable", Snackbar.LENGTH_SHORT)
                .show()

    }

}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
