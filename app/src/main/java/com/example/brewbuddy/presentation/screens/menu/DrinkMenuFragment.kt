package com.example.brewbuddy.presentation.screens.menu

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brewbuddy.CoffeeAdapter
import com.example.brewbuddy.R
import com.example.brewbuddy.presentation.viewmodel.CoffeeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DrinkMenuFragment : Fragment(R.layout.fragment_drink_menu) {

    private val viewModel: CoffeeViewModel by viewModels()
    private lateinit var adapter: CoffeeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.coffeeRecyclerView)
        adapter = CoffeeAdapter(emptyList()) { coffee ->
            // TODO: Navigate to details
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val searchBar = view.findViewById<EditText>(R.id.searchBar)
        searchBar.addTextChangedListener { viewModel.filter(it.toString()) }

        val btnHot = view.findViewById<Button>(R.id.buttonHot)
        val btnCold = view.findViewById<Button>(R.id.buttonCold)

        btnHot.setOnClickListener { viewModel.filterByCategory("HOT") }
        btnCold.setOnClickListener { viewModel.filterByCategory("COLD") }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.coffeeList.collectLatest { adapter.updateList(it) }
        }
    }
}
