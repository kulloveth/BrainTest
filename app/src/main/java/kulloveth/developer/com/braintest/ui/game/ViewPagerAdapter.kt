package kulloveth.developer.com.braintest.ui.game

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kulloveth.developer.com.braintest.data.models.Questions
import kulloveth.developer.com.braintest.data.models.Quiz


class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    val arrayList: ArrayList<Questions> = ArrayList()
    override fun getItemCount(): Int = arrayList.size


    fun setData(quiz: List<Questions>) {
        arrayList.addAll(quiz)
        notifyDataSetChanged()
    }

    override fun createFragment(position: Int): Fragment {
        return QuestionsFragment()
    }


}


