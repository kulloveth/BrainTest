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
import kotlinx.android.synthetic.main.fragment_game.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.repository.UserRepository
import kulloveth.developer.com.braintest.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var factory: GameViewModelFactory
    var repository = UserRepository()
    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        factory = GameViewModelFactory(repository)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        viewModel = ViewModelProvider(this, factory).get(GameViewModel::class.java)

        viewModel.fetchquiz().observe(this, Observer {
            optionGroup.visibility = View.VISIBLE
            timer()
            question.text = it.questions[viewModel.count].question
            optionOne.text = it.questions[viewModel.count].answers[viewModel.oCount].option
            optionTwo.text = it.questions[viewModel.count].answers[viewModel.oCount + 1].option
            optionThree.text = it.questions[viewModel.count].answers[viewModel.oCount + 2].option
            optionFour.text = it.questions[viewModel.count].answers[viewModel.oCount + 3].option
            next.setOnClickListener { v ->
                it.questions.forEach { ques ->

                    viewModel.quest = it.questions[viewModel.count + 1].question
                    viewModel.optionO =
                        it.questions[viewModel.count + 1].answers[viewModel.oCount].option
                    viewModel.optionT =
                        it.questions[viewModel.count + 1].answers[viewModel.oCount + 1].option
                    viewModel.optionTh =
                        it.questions[viewModel.count + 1].answers[viewModel.oCount + 2].option
                    viewModel.optionF =
                        it.questions[viewModel.count + 1].answers[viewModel.oCount + 3].option
                    viewModel.count++
                    if (viewModel.count == it.questions.size - 1) {
                        viewModel.count = 0
                    }
                }

                question.text = viewModel.quest
                optionOne.text = viewModel.optionO
                optionTwo.text = viewModel.optionT
                optionThree.text = viewModel.optionTh
                optionFour.text = viewModel.optionF

            }
            //Log.d("quizz", "" + it.questions[count + 2])
        })
        binding.viewmodel = viewModel
        return binding.root
    }

    fun timer() {

        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timer.text = "You have " + millisUntilFinished / 1000 + " remaining"
            }

            override fun onFinish() {
                timer.text = "Time is up!"
            }
        }.start()
    }

    fun roundUp() {}
}
