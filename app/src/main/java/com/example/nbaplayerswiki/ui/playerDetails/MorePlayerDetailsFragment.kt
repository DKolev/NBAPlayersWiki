package com.example.nbaplayerswiki.ui.playerDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.nbaplayerswiki.databinding.FragmentMorePlayerDetailsBinding

class MorePlayerDetailsFragment : Fragment() {

    private val viewModel: MorePlayerDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMorePlayerDetailsBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the MorePlayerDetailsViewModel
        binding.viewModel = viewModel

        return binding.root
    }
}