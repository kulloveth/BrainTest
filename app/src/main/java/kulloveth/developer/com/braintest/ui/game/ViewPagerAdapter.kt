package kulloveth.developer.com.braintest.ui.game

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kulloveth.developer.com.braintest.data.models.Question


class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val arrayList: ArrayList<Question> = ArrayList()
    override fun getItemCount(): Int = arrayList.size


    fun setData(questions: List<Question>) {
        arrayList.addAll(questions)
        notifyDataSetChanged()
    }

    override fun createFragment(position: Int): Fragment {
        return QuestionFragment.newInstance(arrayList[position])
    }


}


