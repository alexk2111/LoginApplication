package com.onix.internship.okucherenko.ui.search

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SearchViewPager2Adapter(fragment: Fragment, private val fragList: List<Fragment>): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return fragList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragList[position]
    }

}