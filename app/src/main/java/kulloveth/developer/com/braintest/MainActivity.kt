package kulloveth.developer.com.braintest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_choose_sign_in_method.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailOption.setOnClickListener {
            it.findNavController().navigate(R.id.action_chooseSignInMethodFragment_to_emailSignIn)
        }

        gmailOption.setOnClickListener {
            Snackbar.make(it, "Google Option is currently unavailable", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

}
