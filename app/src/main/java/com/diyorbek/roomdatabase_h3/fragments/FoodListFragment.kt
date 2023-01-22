package com.diyorbek.roomdatabase_h3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.diyorbek.roomdatabase_h3.R
import com.diyorbek.roomdatabase_h3.adapter.FoodAdapter
import com.diyorbek.roomdatabase_h3.database.FoodDatabase
import com.diyorbek.roomdatabase_h3.databinding.FragmentFoodListBinding

class FoodListFragment : Fragment(R.layout.fragment_food_list) {
    private var _binding: FragmentFoodListBinding? = null
    private val binding get() = _binding!!
    private val foodAdapter by lazy { FoodAdapter() }
    private val foodDatabase by lazy { FoodDatabase(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFoodListBinding.bind(view)
        allCode()
    }

    private fun allCode() {
        binding.rv.apply {
            adapter = foodAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        foodAdapter.setList(foodDatabase.dao.getAllFoods().toMutableList())
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_foodListFragment_to_addFoodFragment)
        }
        foodAdapter.onClick = {
            val bundle = bundleOf("food" to it)
            findNavController().navigate(R.id.action_foodListFragment_to_detailFragment, bundle)
        }
    }
}