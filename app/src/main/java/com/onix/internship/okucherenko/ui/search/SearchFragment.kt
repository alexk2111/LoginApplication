package com.onix.internship.okucherenko.ui.search

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.SearchFragmentBinding
import com.onix.internship.okucherenko.ui.search.extended.ExtendedFragment
import com.onix.internship.okucherenko.ui.search.simple.SimpleFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<SearchFragmentBinding>(R.layout.search_fragment) {
    override val viewModel: SearchViewModel by viewModel()

    private val fragList = listOf(
        SimpleFragment(),
        ExtendedFragment()
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SearchViewPager2Adapter(this, fragList)
        binding.searchViewPager2.adapter = adapter
        val fragListTitles = resources.getStringArray(R.array.search_tab_item)
        TabLayoutMediator(binding.searchTabLayout, binding.searchViewPager2) {
            tab, pos -> tab.text = fragListTitles[pos]
        }.attach()
    }


}