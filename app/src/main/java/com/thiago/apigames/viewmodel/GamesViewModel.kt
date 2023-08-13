package com.thiago.apigames.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiago.apigames.model.GameList
import com.thiago.apigames.repository.GamesRepository
import com.thiago.apigames.state.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val repo: GamesRepository
) : ViewModel() {
    private val _games = MutableStateFlow<List<GameList>>(emptyList())
    val games = _games.asStateFlow()

    var state by mutableStateOf(GameState())
        private set

    init {
        fetchGames()
    }

    private fun fetchGames() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = repo.getGames()
                _games.value = result ?: emptyList()
            }
        }
    }

    fun getGameById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result = repo.getGameById(id)
                state = state.copy(
                    name = result?.name ?: "",
                    description_raw = result?.description_raw ?: "",
                    metacritic = result?.metacritic ?: 111,
                    website = result?.website ?: "Seu Web",
                    background_image = result?.background_image ?: ""

                )
            }
        }
    }


    fun clean() {

        state = state.copy(
            name =  "",
            description_raw = "",
            metacritic =  111,
            website =  "",
            background_image = ""
        )

    }
}