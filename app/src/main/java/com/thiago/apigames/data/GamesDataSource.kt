package com.thiago.apigames.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thiago.apigames.model.GameList
import com.thiago.apigames.repository.GamesRepository

class GamesDataSource(private val repo:GamesRepository):PagingSource<Int,GameList>() {
    override fun getRefreshKey(state: PagingState<Int, GameList>): Int? {
       return  state.anchorPosition?.let { anchorPosition->
           val anchorPage = state.closestPageToPosition(anchorPosition)
           anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
       }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameList> {
    return try {

        val nextPagerNumber = params.key ?: 1
        val response = repo.getGamePaging(nextPagerNumber,3)
        LoadResult.Page(
            data = response.results,
            prevKey = null,
            nextKey = if(response.results.isNotEmpty()) nextPagerNumber + 1 else null

        )

    }catch (e:Exception){
        LoadResult.Error(e)
    }
    }


}