package com.example.brewbuddy.presentation.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brewbuddy.R
import com.example.brewbuddy.databinding.FragmentMainBinding
import com.example.brewbuddy.presentation.screens.drink_menu.DrinkMenuFragment
import com.example.brewbuddy.presentation.screens.favorites.FavoritesFragment
import com.example.brewbuddy.presentation.screens.home.HomeFragment
import com.example.brewbuddy.presentation.screens.order.OrderFragment

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userName = "John Smith"
        childFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container_view, HomeFragment())
            .commit()
        binding.tvMainAppBarTitle.text="Good day, $userName"
        val bottomBar = binding.bottomNavigation
        bottomBar.setOnItemSelectedListener {
            return@setOnItemSelectedListener when (it.itemId) {
                R.id.home_nav_bar_item -> {
                    // Todo: Add here user name
                    binding.tvMainAppBarTitle.text="Good day, $userName"
                    childFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container_view, HomeFragment())
                        .commit()
                    true
                }

                R.id.drink_menu_nav_bar_item -> {
                    binding.tvMainAppBarTitle.text="What would you like to drink today?"
                    childFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container_view, DrinkMenuFragment())
                        .commit()
                    true
                }

                R.id.orders_nav_bar_item -> {
                    binding.tvMainAppBarTitle.text="Your orders"
                    childFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container_view, OrderFragment())
                        .commit()
                    true
                }

                R.id.favorites_nav_bar_item ->{
                    binding.tvMainAppBarTitle.text="Your favorite drinks to lighten up your day"
                    childFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container_view, FavoritesFragment())
                        .commit()
                    true
                }

                else -> true
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}