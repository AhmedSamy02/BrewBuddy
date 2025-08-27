package com.example.brewbuddy.presentation.screens.entername

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.compose.ui.text.input.EditCommand
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.brewbuddy.R
import com.example.brewbuddy.data.repository.impl.UserRepositoryImpl
import com.example.brewbuddy.domain.usecase.GetUserNameUseCase
import com.example.brewbuddy.domain.usecase.SaveUserNameUseCase
import com.example.brewbuddy.presentation.viewmodel.EnterNameViewModel
import kotlinx.coroutines.launch


class EnterNameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel: EnterNameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.entername_screen, container, false)
    }


    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // val sharedPref = requireActivity().getSharedPreferences("Prefs", Context.MODE_PRIVATE)
        val repository = UserRepositoryImpl(requireContext())
        val saveNameUseCase = SaveUserNameUseCase(repository)
        val getNameUseCase = GetUserNameUseCase(repository)

        viewModel = EnterNameViewModel(saveNameUseCase, getNameUseCase)


        val enterNameEditText = view.findViewById<EditText>(R.id.enterNameEditText)
        val saveNameButton = view.findViewById<Button>(R.id.saveNameButton)


        saveNameButton.setOnClickListener {
            val name = enterNameEditText.text.toString().trim()

            if (name.isBlank()) {
                Toast.makeText(requireContext(), "Please enter your name!!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.saveName(name)
                findNavController().navigate(R.id.toMainFragment)
            }
        }


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.userName.collect { name ->
                if (!name.isNullOrBlank()) {
                    findNavController().navigate(R.id.MainFragment)
                }
            }
        }
    }
}