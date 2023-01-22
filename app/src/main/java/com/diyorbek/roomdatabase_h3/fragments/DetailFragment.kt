package com.diyorbek.roomdatabase_h3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.diyorbek.roomdatabase_h3.R
import com.diyorbek.roomdatabase_h3.adapter.FoodAdapter
import com.diyorbek.roomdatabase_h3.database.FoodDatabase
import com.diyorbek.roomdatabase_h3.database.FoodEntity
import com.diyorbek.roomdatabase_h3.databinding.FragmentAddFoodBinding
import com.diyorbek.roomdatabase_h3.databinding.FragmentDetailBinding
import com.diyorbek.roomdatabase_h3.util.toBitmap
import com.diyorbek.roomdatabase_h3.util.toByteArray

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val foodAdapter by lazy { FoodAdapter() }
    private val foodDatabase by lazy { FoodDatabase(requireContext()) }
    private var foodEntityy: FoodEntity? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        foodEntityy = arguments?.getParcelable("food")

        allCode()
    }

    private fun allCode() {
        binding.imageView4.setImageBitmap(foodEntityy?.img?.toBitmap())
        binding.foodName.text = foodEntityy?.name
        binding.foodCountry.text = foodEntityy?.country
        binding.foodRating.text = foodEntityy?.rating

        binding.btnEdit.setOnClickListener {
            val bundle = bundleOf("food" to foodEntityy)
            findNavController().navigate(R.id.action_detailFragment_to_addFoodFragment, bundle)
        }

        binding.btndelete.setOnClickListener {
            foodDatabase.dao.deleteFood(foodEntityy!!)
            Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

}