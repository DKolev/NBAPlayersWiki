package com.example.nbaplayerswiki.ui.playerDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaplayerswiki.model.PlayerSeasonAverage
import com.example.nbaplayerswiki.network.BalldontlieApi
import kotlinx.coroutines.launch

class MorePlayerDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    // Getting the player ID which is needed for the request for the player's season average
    val id:Int? = savedStateHandle["playerId"]

    // The internal MutableLiveData that stores the the array with player's more details info
    private val _morePlayerDetails = MutableLiveData<Array<PlayerSeasonAverage>>()

    // The external immutable LiveData for the array of player's more details info
    val morePlayerDetails: LiveData<Array<PlayerSeasonAverage>> = _morePlayerDetails

    /**
     * Call getMorePlayerDetails() on init so we can display the information immediately.
     */
    init {
        if (id != null) {
            getMorePlayerDetails(id)
        }
    }

    /**
     * Gets Player's Season Avarage information from the Balldontlie API Retrofit service and updates the
     * [morePlayerDetails] [LiveData] [Array].
     */
    private fun getMorePlayerDetails(playerId: Int) {

        viewModelScope.launch {
            // currently season is hardcoded to 2018 since if left without a param,
            // i.e. default season, for some players there is no data returned
            // TODO: maybe make a dropdown menu with a different season for the user to manually choose from
            try {
                _morePlayerDetails.value = BalldontlieApi.retrofitService.getSeasonAverages(2018, playerId).data
            } catch (e: Exception) {

            }
        }
    }
}