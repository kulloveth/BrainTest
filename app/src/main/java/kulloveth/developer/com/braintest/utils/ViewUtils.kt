package kulloveth.developer.com.braintest.utils

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.navigation.findNavController
import kulloveth.developer.com.braintest.MainActivity
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.ui.game.GameFragment

fun View.signUptoGameFragment() {
this.findNavController().navigate(R.id.action_emailSignUp_to_gameFragment)
}

fun View.signIntoGameFragment() {
    this.findNavController().navigate(R.id.action_emailSignIn_to_gameFragment)
}

fun Context.startMainActivity() {
    Intent(this, MainActivity::class.java).also {
        this.startActivity(it)
    }
}