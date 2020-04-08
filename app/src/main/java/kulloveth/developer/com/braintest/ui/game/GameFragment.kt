package kulloveth.developer.com.braintest.ui.game

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_game.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.repository.UserRepository
import kulloveth.developer.com.braintest.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var factory: GameViewModelFactory
    var repository = UserRepository()
    private lateinit var viewModel: GameViewModel
    internal lateinit var countDownTimer: CountDownTimer
    internal var gameStarted = false
    private lateinit var binding: FragmentGameBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    var score = 0
    var timeRemaining: Long = 30000
    var initialCountdown: Long = 1000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        factory = GameViewModelFactory(repository)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        viewModel = ViewModelProvider(this, factory).get(GameViewModel::class.java)


//        if(!gameStarted){
//            countDownTimer.start()
//            gameStarted = true
//        }
        //setupQuiz()
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewpager2.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewpager2) { tab, position ->
            val questionNo = position + 1
            tab.text = "Question$questionNo"


        }.attach()
        setupQuiz()
        resetGame()
    }

    fun resetGame() {
        score = 0
        gameScore.text = getString(R.string.game_score, score)
        val initialTimeLeft = timeRemaining / initialCountdown
        timer.text = getString(R.string.timer, initialTimeLeft)
        countDownTimer = object : CountDownTimer(timeRemaining, initialCountdown) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
                val timeleft = millisUntilFinished / initialCountdown
                timer.text = getString(R.string.timer, timeleft)
            }

            override fun onFinish() {
                endGame()
            }
        }.start()
        gameStarted = false
    }

    private fun endGame() {
        Snackbar.make(requireView(), "Game OOver", Snackbar.LENGTH_LONG).show()
        resetGame()
    }

    fun setupQuiz() {

        activity.let {
            viewModel.fetchquiz().observe(this, Observer {
                viewPagerAdapter.setData(it.questions)
            })

        }


    }

}
