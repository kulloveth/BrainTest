package kulloveth.developer.com.braintest.ui.game


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_answers.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Answer
import kulloveth.developer.com.braintest.data.models.Question
import kulloveth.developer.com.braintest.data.models.Quiz
import kulloveth.developer.com.braintest.data.repository.UserRepository

/**
 * A simple [Fragment] subclass.
 */
class AnswersFragment : Fragment() {
    var answers: ArrayList<Answer>? = null
    var question: Question? = null
    private lateinit var factory: GameViewModelFactory
    var repository = UserRepository()
    private lateinit var viewModel: GameViewModel
    private lateinit var adapter: GamesAdapter

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
        viewModel = ViewModelProvider(this, factory).get(GameViewModel::class.java)
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_answers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        adapter = GamesAdapter()
        recycler.adapter = adapter
        val questions = ArrayList<Question>()
        questions.add(question!!)
        adapter.submitList(questions)

        adapter.setUpListener(object : GamesAdapter.ItemCLickedListener {
            override fun onItemClicked(isCorrect: Boolean) {
                val message = if (isCorrect) {
                    "correct"
                }else {"wrong"}
                Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
            }


        })


    }




    companion object{
        const val QUESTION_DATA="QUESTION-DATA"
        fun newInstance(question: Question):Fragment{
            val answersFragment = AnswersFragment()
            val bundle = Bundle()
            bundle.putParcelable(QUESTION_DATA,question)
            answersFragment.arguments = bundle
            return answersFragment
        }
    }


}
