package com.example.nbaplayerswiki.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaplayerswiki.R
import com.example.nbaplayerswiki.databinding.FragmentHomeBinding
import com.example.nbaplayerswiki.nav.PlayerCoordinator


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var coordinator: PlayerCoordinator

    private lateinit var binding: FragmentHomeBinding

    private var isSearchVisible = false

    private val adapter = PlayerListAdapter()

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
        binding = FragmentHomeBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the HomeViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the playersList RecyclerView
        binding.playersList.adapter = PlayerListAdapter()

        // Set up the scroll listener for the RecyclerView
        // which is used to hide or show the Search view
        binding.playersList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 && isSearchVisible) {
                    hideSearch()
                } else if (dy < 0 && !isSearchVisible) {
                    showSearch()
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Getting the players list recycler view
        val recyclerView: RecyclerView = view.findViewById(R.id.players_list)

        // Setting up an item click listener in the adapter
        adapter.setOnItemClickListener { playerId ->
            onItemClick(playerId)
        }

        // Adding a line separator for each player item
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        // Setting up the adapter
        recyclerView.adapter = adapter

        // Observe LiveData for changes in the players list
        viewModel.players.observe(viewLifecycleOwner, Observer { players ->
            adapter.setItems(players)
        })

        // Setting up the search view
        binding.search.queryHint = getString(R.string.search_for_a_player)
        binding.search.onActionViewExpanded()
        binding.search.clearFocus()
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                // Filter the players in the list based on the query
                adapter.filterPlayers(newText.orEmpty())
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })
    }

    /**
     * [onItemClick] navigates to the player details page
     */
    private fun onItemClick(playerId: Int) {
        coordinator.navigateToItemDetails(playerId)
    }

    // Showing the Search view
    private fun showSearch() {
        isSearchVisible = true
        binding.search.visibility = View.VISIBLE
    }

    // Hiding the Search view
    private fun hideSearch() {
        isSearchVisible = false
        binding.search.visibility = View.INVISIBLE
    }
}
