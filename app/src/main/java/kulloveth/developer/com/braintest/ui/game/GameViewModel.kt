package kulloveth.developer.com.braintest.ui.game

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Quiz
import kulloveth.developer.com.braintest.data.repository.UserRepository

class GameViewModel(val repository: UserRepository) : ViewModel() {


    private val scoreLiveData = MutableLiveData<Int>()
    fun fetchquiz(): LiveData<Quiz> {
        return repository.quizLiveData
    }

    val user by lazy {
        repository.currentUser()
    }

    fun setScoreLiveData(score: Int) {
        scoreLiveData.value = score
    }

    fun getScoreLiveData(): LiveData<Int> {
        return scoreLiveData
    }

    fun signOut(view: View) {
        view.findNavController().navigate(R.id.action_gameFragment_to_chooseSignInMethodFragment)
        repository.signOutUser()

    }


}