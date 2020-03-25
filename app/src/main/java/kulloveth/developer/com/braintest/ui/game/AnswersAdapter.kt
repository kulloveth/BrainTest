package kulloveth.developer.com.braintest.ui.game

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.answer_item_layout.view.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Answers

class AnswersAdapter : ListAdapter<Answers, AnswersAdapter.MainViewHolder>(
    DiffCallback()
) {
    lateinit var mItemCLicked: ItemCLickedListener


    class DiffCallback : DiffUtil.ItemCallback<Answers>() {
        override fun areItemsTheSame(oldItem: Answers, newItem: Answers): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Answers, newItem: Answers): Boolean {
            return oldItem._id == newItem._id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.answer_item_layout, parent, false)
        return MainViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            mItemCLicked.let {
                mItemCLicked.onItemClicked(getItem(position))

            }

        }


    }

    fun setUpListener(itemCLicked: ItemCLickedListener) {
        mItemCLicked = itemCLicked
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(answers: Answers) {
            itemView.answers.text = answers.option
            itemView.answers.setOnClickListener {
                if(answers.value){
                    itemView.answers.setBackgroundColor(Color.GREEN)
                }else{
                    itemView.answers.setBackgroundColor(Color.RED)
                }
            }
        }
    }

    interface ItemCLickedListener {
        fun onItemClicked(answers: Answers)
    }

}