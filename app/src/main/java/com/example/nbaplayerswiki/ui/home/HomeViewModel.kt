package com.example.nbaplayerswiki.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaplayerswiki.model.Player
import com.example.nbaplayerswiki.network.BalldontlieApi
import kotlinx.coroutines.launch

enum class BalldontlieApiStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<BalldontlieApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<BalldontlieApiStatus> = _status

    // The internal MutableLiveData that stores the list of players of the most recent request
    private val _players = MutableLiveData<List<Player>>()

    // The external immutable LiveData for the list of all players
    val players: LiveData<List<Player>> = _players

    /**
     * Call getAllPlayers() on init so we can display status (loading or network error)
     * and request the players list from the API. If we get the list, it will be displayed
     */
    init {
        getAllPlayers()
    }

    /**
     * Gets Players information from the BallDontlie API Retrofit service and updates the
     * [Player] [List] [LiveData].
     */
    private fun getAllPlayers() {

        viewModelScope.launch {
            _status.value = BalldontlieApiStatus.LOADING
            try {
                _players.value = BalldontlieApi.retrofitService.getPlayers().data
                _status.value = BalldontlieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = BalldontlieApiStatus.ERROR
                _players.value = listOf()
            }
        }
    }
}