package kulloveth.developer.com.braintest.ui.game

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_layout.view.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Question

class QuestionAdapter : ListAdapter<Question, QuestionAdapter.MainViewHolder>(
    DiffCallback()
) {
    lateinit var mItemCLicked: ItemCLickedListener

    class DiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem._id == newItem._id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_layout, parent, false)
        return MainViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        var questions: Question = getItem(position)
        when (position) {
            0 -> {
                holder.itemView.option.check(R.id.optioneOne)

            }
            1 -> {
                holder.itemView.option.check(R.id.optioneTwo)

            }
            2 -> {
                holder.itemView.option.check(R.id.optioneThree)

            }
            3 -> {
                holder.itemView.option.check(R.id.optioneFour)

            }
            else -> {
                holder.itemView.option.clearCheck()
            }
        }
        holder.itemView.question.text = questions.question
        val count = 0

        holder.itemView.optioneOne.text = questions.answers[count].option
        holder.itemView.optioneTwo.text = questions.answers[count + 1].option
        holder.itemView.optioneThree.text = questions.answers[count + 2].option
        holder.itemView.optioneFour.text = questions.answers[count + 3].option


        // val rbSelected: RadioButton = itemView.findViewById(itemView.option.checkedRadioButtonId)


        holder.itemView.option.setOnCheckedChangeListener { group, checkedId ->
            Log.d("check", "I'm in the check change")
            var isCorrect = false
            when (checkedId) {
                R.id.optioneOne -> {
                    isCorrect = questions.answers[count].value
                    Log.d("answer1", "$isCorrect")
                }

                R.id.optioneTwo -> {
                    isCorrect = questions.answers[count + 1].value
                    Log.d("answer2", " $isCorrect")
                }

                R.id.optioneThree -> {
                    isCorrect = questions.answers[count + 2].value
                    Log.d("answer3", " $isCorrect")
                }

                R.id.optioneFour -> {
                    isCorrect = questions.answers[count + 3].value

                    Log.d("answer4", " ${isCorrect}")
                }

            }

            mItemCLicked.onItemClicked(isCorrect)


        }


    }

    fun setUpListener(itemCLicked: ItemCLickedListener) {
        mItemCLicked = itemCLicked
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface ItemCLickedListener {
        fun onItemClicked(isCorrect: Boolean)
    }


}