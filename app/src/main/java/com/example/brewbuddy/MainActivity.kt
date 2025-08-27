package com.example.brewbuddy

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brewbuddy.adapter.CoffeeAdapter
import com.example.brewbuddy.data.remote.api.RetrofitProvider
import kotlinx.coroutines.launch

class MenuActivity : AppCompatActivity() {

    private lateinit var coffeeAdapter: CoffeeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: EditText
    private lateinit var buttonCold: Button
    private lateinit var buttonHot: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_screen)

        initViews()
        setupRecyclerView()
        setupButtons()
        setupSearchBar()

        // Default: show cold coffees first
        selectButton(buttonCold, buttonHot)
        fetchCoffee(isCold = true)
    }

    /** Initialize UI components */
    private fun initViews() {
        recyclerView = findViewById(R.id.coffeeRecyclerView)
        searchBar = findViewById(R.id.searchBar)
        buttonCold = findViewById(R.id.buttonCold)
        buttonHot = findViewById(R.id.buttonHot)
    }

    /** Setup RecyclerView with adapter */
    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        coffeeAdapter = CoffeeAdapter(emptyList()) { coffee ->
            Toast.makeText(this, "${coffee.title} added to cart!", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = coffeeAdapter
    }

    /** Setup Cold/Hot buttons */
    private fun setupButtons() {
        buttonCold.setOnClickListener {
            selectButton(buttonCold, buttonHot)
            fetchCoffee(isCold = true)
        }

        buttonHot.setOnClickListener {
            selectButton(buttonHot, buttonCold)
            fetchCoffee(isCold = false)
        }
    }

    /** Setup search bar for filtering */
    private fun setupSearchBar() {
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                coffeeAdapter.filter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun fetchCoffee(isCold: Boolean) {
        lifecycleScope.launch {
            try {
                val coffees = if (isCold) {
                    RetrofitProvider.api.getIced()
                } else {
                    RetrofitProvider.api.getHot()
                }
                coffeeAdapter.updateData(coffees)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MenuActivity, "Failed to load coffee menu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun selectButton(selected: Button, unselected: Button) {
        selected.apply {
            isSelected = true
            setTextColor(Color.WHITE)
        }
        unselected.apply {
            isSelected = false
            setTextColor(Color.parseColor("#834D1E"))
        }
    }
}
