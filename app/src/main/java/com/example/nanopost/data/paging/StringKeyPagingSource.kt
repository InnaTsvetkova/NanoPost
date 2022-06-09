package com.example.nanopost.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nanopost.data.api.PageDataResponse
import com.example.nanopost.data.api.models.ApiPost
import java.util.concurrent.CancellationException

class StringKeyPagingSource<T : Any>(
    private val getData: suspend (LoadParams<String>) -> PageDataResponse<T>
) : PagingSource<String, T>() {

    override fun getRefreshKey(state: PagingState<String, T>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, T> {
        return try {
            val response = getData(params)
            val nextKey = response.offset?.takeIf { it.toInt() < response.total }
            LoadResult.Page(response.items, null, nextKey)
        } catch (exception: CancellationException) {
            throw exception
        } catch (exception: Throwable) {
            LoadResult.Error(exception)
        }
    }
}
