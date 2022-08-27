package com.onix.internship.okucherenko.ui.memelist

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.data.model.Data
import com.onix.internship.okucherenko.data.repository.MemeRepository
import kotlinx.coroutines.flow.Flow

private const val ITEMS_PER_PAGE = 1

class MemelistViewModel(private val repository: MemeRepository) : BaseViewModel() {

    val items: Flow<PagingData<Data>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { repository.memePagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)
}
