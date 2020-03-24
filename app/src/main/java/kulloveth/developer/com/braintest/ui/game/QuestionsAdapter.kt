package kulloveth.developer.com.braintest.ui.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_layout.view.*
import kulloveth.developer.com.braintest.R
import kulloveth.developer.com.braintest.data.models.Questions

class QuestionsAdapter : ListAdapter<Questions, QuestionsAdapter.MainViewHolder>(
    DiffCallback()
) {
    lateinit var mItemCLicked: ItemCLickedListener


    class DiffCallback : DiffUtil.ItemCallback<Questions>() {
        override fun areItemsTheSame(oldItem: Questions, newItem: Questions): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Questions, newItem: Questions): Boolean {
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

        fun bind(questions: Questions) {
            itemView.question.text = questions.question
            questions.answers.forEach {
                itemView.option.text = it.option
            }
        }
    }

    interface ItemCLickedListener {
        fun onItemClicked(countryDetails: Questions)
    }

}