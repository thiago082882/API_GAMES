package com.thiago.apigames.data

import com.thiago.apigames.model.GamesModel
import com.thiago.apigames.model.SingleGameModel
import com.thiago.apigames.utils.Constants.Companion.API_KEY
import com.thiago.apigames.utils.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GameApi {

    @GET(ENDPOINT + API_KEY)
    suspend fun getGames(): Response<GamesModel>

    @GET("$ENDPOINT/{id}$API_KEY")
    suspend fun getGameById(@Path(value = "id")id:Int): Response<SingleGameModel>

}