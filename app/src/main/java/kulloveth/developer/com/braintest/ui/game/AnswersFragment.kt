package kulloveth.developer.com.braintest.ui.game


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_game.recycler
import kotlinx.android.synthetic.main.fragment_answers.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Answer

/**
 * A simple [Fragment] subclass.
 */
class AnswersFragment : Fragment() {
    var answers: ArrayList<Answer>? = null
    var question: String? = null
    private lateinit var adapter: AnswersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        answers = arguments?.getParcelableArrayList<Answer>("answers")
        question = arguments?.getString("question")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_answers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        adapter = AnswersAdapter()
        recycler.adapter = adapter
        questions.text = question
        adapter.submitList(answers)
        adapter.setUpListener(object : AnswersAdapter.ItemCLickedListener{
            override fun onItemClicked(answers: Answer) {
                if(answers.value){

                }
            }
        })


    }


}
