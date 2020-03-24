package kulloveth.developer.com.braintest.ui.game

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_game.*
import kulloveth.developer.com.braintest.data.repository.UserRepository
import kulloveth.developer.com.braintest.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var factory: GameViewModelFactory
    var repository = UserRepository()
    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding
    var count = 0
    var oCount = 0
    var quest: String? = null
    var optionO: String? = null
    var optionT: String? = null
    var optionTh: String? = null
    var optionF: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        factory = GameViewModelFactory(repository)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        viewModel = ViewModelProvider(this, factory).get(GameViewModel::class.java)

        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                mTextField.setText("done!")
            }
        }.start()
        viewModel.fetchquiz().observe(this, Observer {
            optionGroup.visibility = View.VISIBLE
            question.text = it.questions[count].question
            optionOne.text = it.questions[count].answers[oCount].option
            optionTwo.text = it.questions[count].answers[oCount+1].option
            optionThree.text = it.questions[count].answers[oCount+2].option
            optionFour.text = it.questions[count].answers[oCount+3].option
            next.setOnClickListener { v ->
                it.questions.forEach { ques ->
                    quest = it.questions[count + 1].question
                    optionO= it.questions[count+1].answers[oCount].option
                    optionT = it.questions[count+1].answers[oCount+1].option
                    optionTh = it.questions[count+1].answers[oCount+2].option
                    optionF = it.questions[count+1].answers[oCount+3].option
                    count++
                    if (count == it.questions.size - 1) {
                           count = 0
                    }
                }

                question.text = quest
                optionOne.text = optionO
                optionTwo.text = optionT
                optionThree.text = optionTh
                optionFour.text = optionF

            }
            Log.d("quizz", "" + it.questions[count + 2])
        })
        binding.viewmodel = viewModel
        return binding.root
    }
}
