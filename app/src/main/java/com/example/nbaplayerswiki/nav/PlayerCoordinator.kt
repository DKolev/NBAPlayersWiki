package com.example.nbaplayerswiki.nav

import androidx.navigation.NavController
import com.example.nbaplayerswiki.ui.home.HomeFragmentDirections
import com.example.nbaplayerswiki.ui.playerDetails.PlayerDetailsFragmentDirections


class PlayerCoordinator(private val navController: NavController) {

    /**
     * Used to navigate from a certain player to the details screen about him
     */
    fun navigateToItemDetails(playerId: Int) {
        val action = HomeFragmentDirections.actionNavigationHomeToNavigationPlayerDetails(playerId)
        navController.navigate(action)
    }

    /**
     * Used to navigate from a certain player's details page to a page with season averages
     * details for that player for a certain season
     */
    fun navigateToMorePlayerDetails(playerId: Int) {
        val action = PlayerDetailsFragmentDirections.actionNavigationPlayerDetailsToNavigationPlayerMoreDetails(playerId)
        navController.navigate(action)
    }
}
