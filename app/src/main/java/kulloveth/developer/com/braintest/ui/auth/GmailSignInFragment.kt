package kulloveth.developer.com.braintest.ui.auth


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kulloveth.developer.com.braintest.R

/**
 * A simple [Fragment] subclass.
 */
class GmailSignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gmail_sign_in, container, false)
    }


}
