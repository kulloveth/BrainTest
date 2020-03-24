package kulloveth.developer.com.braintest.ui.game


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_questions.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Questions
import kulloveth.developer.com.braintest.data.models.Quiz

/**
 * A simple [Fragment] subclass.
 */
class QuestionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_questions, container, false)
    }



}
