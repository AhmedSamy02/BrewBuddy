package com.example.brewbuddy.presentation.screens.details_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brewbuddy.R
import com.example.brewbuddy.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPlus.setOnClickListener {
            var count = binding.tvQuantity.text.toString().toInt()
            count++
            binding.tvQuantity.text = count.toString()
        }

        binding.btnMinus.setOnClickListener {
            var count = binding.tvQuantity.text.toString().toInt()
            if (count > 0) {
                count--
                binding.tvQuantity.text = count.toString()
            }
        }


    }


}