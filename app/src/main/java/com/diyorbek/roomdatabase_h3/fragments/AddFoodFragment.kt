package com.diyorbek.roomdatabase_h3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.diyorbek.roomdatabase_h3.R
import com.diyorbek.roomdatabase_h3.adapter.FoodAdapter
import com.diyorbek.roomdatabase_h3.database.FoodDatabase
import com.diyorbek.roomdatabase_h3.database.FoodEntity
import com.diyorbek.roomdatabase_h3.databinding.FragmentAddFoodBinding
import com.diyorbek.roomdatabase_h3.databinding.FragmentFoodListBinding
import com.diyorbek.roomdatabase_h3.util.toBitmap
import com.diyorbek.roomdatabase_h3.util.toByteArray

class AddFoodFragment : Fragment(R.layout.fragment_add_food) {
    private var _binding: FragmentAddFoodBinding? = null
    private val binding get() = _binding!!
    private val foodDatabase by lazy { FoodDatabase(requireContext()) }
    private var foodEntityy: FoodEntity? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddFoodBinding.bind(view)
        foodEntityy = arguments?.getParcelable("food")

        allCode()
    }

    private fun allCode() {

        binding.back.setOnClickListener {
            findNavController().popBackStack()
            findNavController().popBackStack()
        }
        if (foodEntityy == null) {
            binding.foodImg.setOnClickListener {
                imageLauncher.launch("image/*")
            }
            binding.titleText.text = "Add Food"
            binding.btnSaveEdit.text = "Save"
            binding.btnSaveEdit.setOnClickListener {
                foodDatabase.dao.saveFood(
                    FoodEntity(
                        img = binding.foodImg.toByteArray(),
                        name = binding.name.text.toString().trim(),
                        country = binding.country.text.toString().trim(),
                        rating = binding.rating.text.toString().trim()
                    )
                )
                binding.foodImg.setImageResource(R.drawable.baseline_image_24)
                binding.name.text?.clear()
                binding.country.text?.clear()
                binding.rating.text?.clear()
                Toast.makeText(requireContext(), "Successfully saved", Toast.LENGTH_SHORT).show()
            }
        } else {
            binding.foodImg.setOnClickListener {
                binding.foodImg.setImageResource(R.drawable.baseline_image_24)
                imageLauncher.launch("image/*")
            }
            binding.titleText.text = "Edit Food"
            binding.btnSaveEdit.text = "Commit changes"
            binding.apply {
                foodImg.setImageBitmap(foodEntityy?.img?.toBitmap())
                name.setText(foodEntityy?.name)
                country.setText(foodEntityy?.country)
                rating.setText(foodEntityy?.rating)
            }
            binding.btnSaveEdit.setOnClickListener {
                foodDatabase.dao.updateFood(
                    FoodEntity(
                        foodEntityy?.id!!,
                        binding.foodImg.toByteArray(),
                        binding.name.text.toString().trim(),
                        binding.country.text.toString().trim(),
                        binding.rating.text.toString().trim()
                    )
                )
                Toast.makeText(requireContext(), "Successfully Edited", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
                findNavController().popBackStack()
            }
        }
    }

    private val imageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it?.let { uri ->
            binding.foodImg.setImageURI(uri)
        }
    }
}