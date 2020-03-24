package kulloveth.developer.com.braintest.ui.game

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Quiz
import kulloveth.developer.com.braintest.data.repository.UserRepository

class GameViewModel(val repository: UserRepository) : ViewModel() {

    var count = 0
    var oCount = 0
    var result = 0
    var quest: String? = null
    var optionO: String? = null
    var optionT: String? = null
    var optionTh: String? = null
    var optionF: String? = null
    fun fetchquiz(): LiveData<Quiz> {
        return repository.quizLiveData
    }

    val user by lazy {
        repository.currentUser()
    }

    fun signOut(view: View) {
        repository.signOutUser()
        view.findNavController().navigate(R.id.action_gameFragment_to_chooseSignInMethodFragment)
    }




}