package kulloveth.developer.com.braintest.ui.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Quiz
import kulloveth.developer.com.braintest.data.repository.UserRepository

class GameViewModel(val repository: UserRepository) : ViewModel() {


    companion object{
        const val DONE = 0L
        const val ONE_SECONDS = 1000L
        const val COUNTDOWN_TIME=40000L
    }
    private val _currentTime = MutableLiveData<Long>()
    val currenTime =  _currentTime

    val currentTimeString = Transformations.map(currenTime){
        time->DateUtils.formatElapsedTime(time)
    }
    private val timer:CountDownTimer

    init {
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECONDS){
            override fun onFinish() {
                _currentTime.value = DONE
            }

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ ONE_SECONDS
            }

        }
        timer.start()
    }


    private val scoreLiveData = MutableLiveData<Int>()
    fun fetchquiz(): LiveData<Quiz> = repository.quizLiveData

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

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }


}