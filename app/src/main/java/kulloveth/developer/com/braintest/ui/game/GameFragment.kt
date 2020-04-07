package kulloveth.developer.com.braintest.ui.game

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_game.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Answer
import kulloveth.developer.com.braintest.data.models.Question
import kulloveth.developer.com.braintest.data.repository.UserRepository
import kulloveth.developer.com.braintest.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var factory: GameViewModelFactory
    var repository = UserRepository()
    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding
    private lateinit var adapter: GamesAdapter
    private lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        factory = GameViewModelFactory(repository)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        viewModel = ViewModelProvider(this, factory).get(GameViewModel::class.java)


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
    }

    fun timer() {

        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //timer.text = "You have " + millisUntilFinished / 1000 + " remaining"
            }

            override fun onFinish() {
                // timer.text = "Time is up!"
                // view?.findNavController()?.navigate(R.id.action_gameFragment_to_quizSummaryFragment)
            }
        }.start()
    }
    fun setupQuiz() {

        activity.let {
            viewModel.fetchquiz().observe(this, Observer {
                viewPagerAdapter.setData(it.questions)
            })

        }


    }

}
