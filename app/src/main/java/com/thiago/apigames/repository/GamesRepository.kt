package com.thiago.apigames.repository

import com.thiago.apigames.data.GameApi
import com.thiago.apigames.model.GameList
import com.thiago.apigames.model.SingleGameModel
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

    suspend fun getGameById(id:Int):SingleGameModel?{
        val response = gameApi.getGameById(id)
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }
}