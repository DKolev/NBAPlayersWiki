package com.example.nbaplayerswiki.ui.playerDetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.nbaplayerswiki.R
import com.example.nbaplayerswiki.databinding.FragmentPlayerDetailsBinding
import com.example.nbaplayerswiki.nav.PlayerCoordinator

class PlayerDetailsFragment : Fragment() {

    private val viewModel: PlayerDetailsViewModel by viewModels()

    private lateinit var coordinator: PlayerCoordinator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        coordinator = PlayerCoordinator(navHostFragment.navController)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPlayerDetailsBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the PlayerDetailsViewModel
        binding.viewModel = viewModel

        // Navigating to the season averages screen for the player
        binding.moreDetailsButton.setOnClickListener {
            viewModel.id?.let { it1 -> coordinator.navigateToMorePlayerDetails(it1) }
        }

        return binding.root
    }
}