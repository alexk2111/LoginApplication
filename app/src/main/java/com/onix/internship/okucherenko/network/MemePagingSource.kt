package com.onix.internship.okucherenko.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onix.internship.okucherenko.data.model.Data

private const val STARTING_KEY = 1

class MemePagingSource : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val start = params.key ?: STARTING_KEY

        val result = MemeApi.retrofitMemeService.getMemes(start)

        return LoadResult.Page(
            result.data,
            prevKey = if (start > STARTING_KEY) start - 1 else null,
            nextKey = start + 1
        )
    }
}