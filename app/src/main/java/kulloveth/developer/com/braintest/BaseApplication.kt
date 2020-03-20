package kulloveth.developer.com.braintest

import android.app.Application
import kulloveth.developer.com.braintest.data.FirebaseSource

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseSource.initializeFirebase()
    }
}