package kulloveth.developer.com.braintest.ui.game


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_game.recycler
import kotlinx.android.synthetic.main.fragment_questions.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Answers

/**
 * A simple [Fragment] subclass.
 */
class AnswersFragment : Fragment() {
    var answers: ArrayList<Answers>? = null
    var question: String? = null
    private lateinit var adapter: AnswersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        answers = arguments?.getParcelableArrayList<Answers>("answers")
        question = arguments?.getString("question")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        adapter = AnswersAdapter()
        recycler.adapter = adapter
        questions.text = question
        adapter.submitList(answers)


    }


}
