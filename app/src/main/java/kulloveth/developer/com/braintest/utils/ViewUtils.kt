package kulloveth.developer.com.braintest.utils

import android.content.Context
import android.content.Intent
import kulloveth.developer.com.braintest.MainActivity
import kulloveth.developer.com.braintest.ui.game.GameActivity

fun Context.startGameActivity() {
    Intent(this, GameActivity::class.java).also {
        this.startActivity(it)
    }
}

fun Context.startMainActivity() {
    Intent(this, MainActivity::class.java).also {
        this.startActivity(it)
    }
}