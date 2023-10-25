package com.example.nbaplayerswiki.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaplayerswiki.databinding.ListViewItemBinding
import com.example.nbaplayerswiki.model.Player

class PlayerListAdapter :
    ListAdapter<Player, PlayerListAdapter.PlayersViewHolder>(DiffCallback) {

    private var onItemClickListener: ((Int) -> Unit)? = null

    private var playersList = mutableListOf<Player>()
    private var filteredPlayersList = mutableListOf<Player>()

    init {
        filteredPlayersList.addAll(playersList)
    }

    override fun getItemCount(): Int {
        return filteredPlayersList.size
    }

    fun setItems(items: List<Player>) {
        playersList.clear()
        playersList.addAll(items)
        filterPlayers("")
    }

    // Filter the list of players based on the query typed in the search view
    fun filterPlayers(query: String) {
        filteredPlayersList.clear()
        for (player in playersList) {
            val queryInFirstName = player.firstName?.contains(query, true) == true
            val queryInLastName = player.lastName?.contains(query, true) == true
            if (queryInFirstName || queryInLastName) { // Adjust based on your data structure
                filteredPlayersList.add(player)
            }
        }
        notifyDataSetChanged()
    }

    class PlayersViewHolder(
        private var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) {
            binding.player = player
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [Player] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayersViewHolder {
        return PlayersViewHolder(
            ListViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val player = filteredPlayersList[position]
        holder.bind(player)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(player.id)
        }
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}