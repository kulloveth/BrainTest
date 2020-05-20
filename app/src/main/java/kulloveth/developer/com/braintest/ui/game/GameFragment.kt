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
    internal var gameStarted = false
    private lateinit var binding: FragmentGameBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    var score = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        factory = GameViewModelFactory(repository)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        activity.let {
            viewModel = ViewModelProvider(requireActivity(), factory).get(GameViewModel::class.java)
        }

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
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
        activity.let {
            viewModel.getScoreLiveData().observe(viewLifecycleOwner, Observer {
                gameScore.text = it.toString()
            })

        }

    }

    fun resetGame() {
        score = 0

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
