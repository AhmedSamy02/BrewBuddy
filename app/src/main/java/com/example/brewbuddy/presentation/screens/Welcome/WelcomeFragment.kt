package com.example.brewbuddy.presentation.screens.Welcome

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.brewbuddy.R
import com.example.brewbuddy.data.repository.impl.UserRepositoryImpl
import com.example.brewbuddy.domain.usecase.GetUserNameUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WelcomeFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.welcome_screen, container, false)
        val mybutton = view.findViewById<Button>(R.id.GetStaredBtn)


        mybutton.setOnClickListener {
            val repository = UserRepositoryImpl(requireContext())
            val getUserNameUseCase= GetUserNameUseCase(repository)

            lifecycleScope.launch {
            //// val sharedPref = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
            val savedName = getUserNameUseCase().first()
            if (savedName != null) {
                findNavController().navigate(R.id.toMainFragment)
            } else {
                findNavController().navigate(R.id.toEnterNameFragment)
            }
        }
    }
        return view
    }
}