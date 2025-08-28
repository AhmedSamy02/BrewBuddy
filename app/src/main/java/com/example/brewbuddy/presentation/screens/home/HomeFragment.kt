package com.example.brewbuddy.presentation.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.brewbuddy.R
import com.example.brewbuddy.databinding.FragmentHomeBinding
import com.example.brewbuddy.presentation.screens.drink_menu.DrinkMenuFragment
import com.example.brewbuddy.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isLoadingBestSeller.observe(this) { loading ->
            if (loading) {
                binding.bestSellerProgressBar.visibility = View.VISIBLE
                binding.clBestSellerData.visibility = View.GONE
            } else {
                binding.bestSellerProgressBar.visibility = View.GONE
                binding.clBestSellerData.visibility = View.VISIBLE
                val bestSeller = viewModel.uiState.value.bestSeller!!
                binding.tvBestSellerTitle.text = bestSeller.title
                Glide.with(this)
                    .load(bestSeller.image)
                    .placeholder(R.drawable.coffee_image_placeholder)

                    .transition(withCrossFade(300))
                    .into(binding.bestSellerIv)
            }
        }
        viewModel.isLoadingRecommendations.observe(this) { loading ->
            if (loading) {
                binding.recommendationProgressBar.visibility = View.VISIBLE
                binding.rvWeekRecommendations.visibility = View.GONE
            } else {
                binding.recommendationProgressBar.visibility = View.GONE
                binding.rvWeekRecommendations.visibility = View.VISIBLE
                val adapter =
                    WeekRecommendationRecyclerViewAdapter(viewModel.uiState.value.recommendations)
                binding.rvWeekRecommendations.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                binding.rvWeekRecommendations.adapter = adapter
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cvIntroductionNewMenu.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container_view, DrinkMenuFragment())
                .commit()
        }
    }

}