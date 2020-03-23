package kulloveth.developer.com.braintest.ui.game

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Quiz
import kulloveth.developer.com.braintest.data.repository.UserRepository

class GameViewModel(val repository: UserRepository) : ViewModel() {

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