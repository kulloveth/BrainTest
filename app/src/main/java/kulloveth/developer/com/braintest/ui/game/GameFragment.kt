package kulloveth.developer.com.braintest.ui.game

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_game.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Questions
import kulloveth.developer.com.braintest.data.repository.UserRepository
import kulloveth.developer.com.braintest.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var factory: GameViewModelFactory
    var repository = UserRepository()
    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding
    private lateinit var adapter: GamesAdapter


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
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        adapter = GamesAdapter()
        recycler.adapter = adapter
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
                adapter.submitList(it.questions)
            })

            adapter.setUpListener(object : GamesAdapter.ItemCLickedListener {
                override fun onItemClicked(questions: Questions) {
                    val bundle = bundleOf(
                        "answers" to questions.answers,
                    "question" to questions.question
                    )
                    view?.findNavController()
                        ?.navigate(R.id.action_gameFragment_to_questionsFragment, bundle)
                }

            })
        }


    }

}
