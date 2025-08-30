package com.example.brewbuddy.presentation.screens.Welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.brewbuddy.R
import com.example.brewbuddy.presentation.viewmodel.EnterNameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WelcomeFragment : Fragment() {
    private val viewModel: EnterNameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.welcome_screen, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        lifecycleScope.launch {
            // Show welcome screen for a few milliseconds
            delay(2000) // 2 seconds - you can adjust this
            
            // Check if user has already saved their name
            val savedName = viewModel.userName.value
            if (!savedName.isNullOrBlank()) {
                // Navigate to main screen if name exists
                findNavController().navigate(R.id.toMainFragment)
            } else {
                // Navigate to enter name screen if no name saved
                findNavController().navigate(R.id.toEnterNameFragment)
            }
        }
    }
}
