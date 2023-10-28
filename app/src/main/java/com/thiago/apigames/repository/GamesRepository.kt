package com.thiago.apigames.repository

import com.thiago.apigames.data.GameApi
import com.thiago.apigames.model.GameList
import com.thiago.apigames.model.GamesModel
import com.thiago.apigames.model.SingleGameModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val gameApi: GameApi
) {
suspend fun getGames():List<GameList>?{
    val response = gameApi.getGames()
    if(response.isSuccessful){
        return response.body()?.results
    }
    return null
}
    suspend fun getGamePaging(page:Int,pageSize:Int):GamesModel{
        delay(3000L)
        return gameApi.getGamesPaging(page,pageSize)
    }


    suspend fun getGameById(id:Int):SingleGameModel?{
        val response = gameApi.getGameById(id)
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }

    suspend fun getGameByName(name:String):SingleGameModel?{
        val response = gameApi.getGameByName(name)
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }
}