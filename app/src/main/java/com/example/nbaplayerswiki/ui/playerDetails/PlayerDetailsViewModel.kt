package com.example.nbaplayerswiki.ui.playerDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaplayerswiki.model.Player
import com.example.nbaplayerswiki.network.BalldontlieApi
import kotlinx.coroutines.launch

class PlayerDetailsViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val id:Int? = savedStateHandle["playerId"]

    // The internal MutableLiveData that stores the the player's details
    private val _playerDetails = MutableLiveData<Player>()

    // The external immutable LiveData for the player's details
    val playerDetails: LiveData<Player> = _playerDetails

    /**
     * Call getPlayerDetails() on init so we can display the player's details immediately.
     */
    init {
        if (id != null) {
            getPlayerDetails(id)
        }
    }

    /**
     * Gets Player details information from the BallDontLie API Retrofit service and updates the
     * [Player] [LiveData].
     * // TODO: Ideally, this second call for the same info as in the previous screen shouldn't be done
     * // TODO: Instead, the info from the 1st call should be set locally (maybe using JetPack Room lib)
     * // TODO: And then just get the information here from the Room DB
     */
    private fun getPlayerDetails(playerId: Int) {

        viewModelScope.launch {
            val playerList = BalldontlieApi.retrofitService.getPlayers().data
            _playerDetails.value = playerList.find {it.id == playerId}
        }
    }
}