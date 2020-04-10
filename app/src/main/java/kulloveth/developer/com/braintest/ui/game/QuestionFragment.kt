package kulloveth.developer.com.braintest.ui.game


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_question.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Answer
import kulloveth.developer.com.braintest.data.models.Question
import kulloveth.developer.com.braintest.data.repository.UserRepository
import kulloveth.developer.com.braintest.utils.addScorePreferences
import kulloveth.developer.com.braintest.utils.getSharedPreferencesValue

/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : Fragment() {
    var answers: ArrayList<Answer>? = null
    var question: Question? = null
    var score = 0
    private lateinit var factory: GameViewModelFactory
    var repository = UserRepository()
    private lateinit var viewModel: GameViewModel
    private lateinit var adapter: QuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        bundle.let {
            question = it?.getParcelable(QUESTION_DATA)
        }
        answers = arguments?.getParcelableArrayList<Answer>("answers")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        factory = GameViewModelFactory(repository)
        activity.let {
            viewModel = ViewModelProvider(requireActivity(), factory).get(GameViewModel::class.java)
        }

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        adapter = QuestionAdapter()
        recycler.adapter = adapter
        activity.let {
            viewModel.setScoreLiveData(getSharedPreferencesValue(requireActivity(), "SCORE"))
        }
        val questions = ArrayList<Question>()
        questions.add(question!!)
        adapter.submitList(questions)

        adapter.setUpListener(object : QuestionAdapter.ItemCLickedListener {
            override fun onItemClicked(isCorrect: Boolean) {
                if (isCorrect) {
                    score = getSharedPreferencesValue(requireActivity(), "SCORE")
                    addScorePreferences(requireActivity(), score.plus(1))
                } else {
                    score = getSharedPreferencesValue(requireActivity(), "SCORE")
                    addScorePreferences(requireActivity(), score.minus(1))
                }

            }


        })

    }
    companion object {
        const val QUESTION_DATA = "QUESTION-DATA"
        fun newInstance(question: Question): Fragment {
            val answersFragment = QuestionFragment()
            val bundle = Bundle()
            bundle.putParcelable(QUESTION_DATA, question)
            answersFragment.arguments = bundle
            return answersFragment
        }
    }


}
