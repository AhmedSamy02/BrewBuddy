package com.example.brewbuddy.presentation.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.brewbuddy.R
import com.example.brewbuddy.data.repository.impl.UserRepositoryImpl
import com.example.brewbuddy.databinding.FragmentMainBinding
import com.example.brewbuddy.domain.usecase.DeleteUserNameUseCase
import com.example.brewbuddy.domain.usecase.GetUserNameUseCase
import com.example.brewbuddy.domain.usecase.SaveUserNameUseCase
import com.example.brewbuddy.presentation.screens.drink_menu.DrinkMenuFragment
import com.example.brewbuddy.presentation.screens.favorites.FavoritesFragment
import com.example.brewbuddy.presentation.screens.home.HomeFragment
import com.example.brewbuddy.presentation.screens.order.OrderFragment
import com.example.brewbuddy.presentation.viewmodel.EnterNameViewModel

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    private lateinit var viewModel : EnterNameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

       /// val sharedPref = requireActivity().getSharedPreferences("Prefs", Context.MODE_PRIVATE)
        val repository = UserRepositoryImpl(requireContext())
        val saveNameUseCase = SaveUserNameUseCase(repository)
        val getNameUseCase = GetUserNameUseCase(repository)
        val deleteUserNameUseCase = DeleteUserNameUseCase(repository)
        viewModel = EnterNameViewModel(saveNameUseCase, getNameUseCase, deleteUserNameUseCase)
        viewModel.getName()


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.userName.collect { name ->
                val displayName = name ?: "Guest"
                binding.tvMainAppBarTitle.text = "Good day, $displayName"
            }
        }

//        val userName = "John Smith"
        childFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container_view, HomeFragment())
            .commit()
//        binding.tvMainAppBarTitle.text="Good day, $userName"



        val bottomBar = binding.bottomNavigation
        bottomBar.setOnItemSelectedListener {
             when (it.itemId) {
                R.id.home_nav_bar_item -> {
                    val getName = viewModel.userName.value?: "Guest"
                    // Todo: Add here user name
                    binding.tvMainAppBarTitle.text="Good day, $getName"
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.main_menu_logoutBtn ->{
                viewModel.logout()
                Toast.makeText(requireContext(),"Logged out successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.toEnterNameFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}