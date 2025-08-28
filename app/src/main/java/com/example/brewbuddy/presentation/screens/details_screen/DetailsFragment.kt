package com.example.brewbuddy.presentation.screens.details_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.brewbuddy.R
import com.example.brewbuddy.databinding.FragmentDetailsBinding
import com.example.brewbuddy.domain.model.Coffee
import com.example.brewbuddy.domain.model.CoffeeCategory
import com.example.brewbuddy.presentation.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeViewModel()

        // For demonstration, set a sample coffee
        // In a real app, you would get this from navigation arguments
        setSampleCoffee()
    }

    private fun setupUI() {
        binding.btnPlus.setOnClickListener {
            viewModel.incrementQuantity()
        }

        binding.btnMinus.setOnClickListener {
            viewModel.decrementQuantity()
        }

        binding.btnBuyNow.setOnClickListener {
            viewModel.proceedToPayment()
        }

        binding.closeBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        
        binding.favouriteBtn.setOnClickListener {
            viewModel.toggleFavorite()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                updateUI(state)
            }
        }
    }

        private fun updateUI(state: com.example.brewbuddy.presentation.viewmodel.DetailsUiState) {
        // Update quantity
        binding.tvQuantity.text = state.quantity.toString()
        
        // Update coffee details
        state.coffee?.let { coffee ->
            binding.productTitle.text = coffee.title
            binding.productDescription.text = coffee.description
            binding.productPrice.text = "Rp ${String.format("%.0f", coffee.price)}"
            // You would set the image here using an image loading library like Glide or Coil
        }
        
        // Update favorite button state
        binding.favouriteBtn.isSelected = state.isFavorite
        
        // Handle navigation to payment
        if (state.shouldNavigateToPayment) {
            navigateToPayment(state.selectedCoffee, state.finalQuantity)
            viewModel.onNavigatedToPayment()
        }
    }
    
    private fun navigateToPayment(coffee: Coffee?, quantity: Int) {
        // TODO: Navigate to payment screen with coffee and quantity data
        coffee?.let {
            Toast.makeText(
                context, 
                "Navigate to Payment: ${it.title} x$quantity = Rp ${String.format("%.0f", it.price * quantity)}", 
                Toast.LENGTH_LONG
            ).show()
            
            // Example of how you would navigate using Navigation Component:
            // val action = DetailsFragmentDirections.actionDetailsToPayment(
            //     coffeeId = it.id,
            //     coffeeName = it.title,
            //     coffeePrice = it.price,
            //     quantity = quantity,
            //     totalAmount = it.price * quantity
            // )
            // findNavController().navigate(action)
        }
    }


    private fun setSampleCoffee() {
        val sampleCoffee = Coffee(
            id = 1,
            title = "Iced Coffee Sweet Heaven",
            description = "Double espresso with condensed milk, served cold",
            ingredients = listOf("Espresso", "Condensed Milk", "Ice"),
            image = "https://images.unsplash.com/photo-1532004491497-ba35c367d634?auto=format&fit=crop&q=80&w=1887&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            price = 20000.0,
            category = CoffeeCategory.COLD,
            isFavorite = false
        )
        viewModel.setCoffee(sampleCoffee)
    }
}