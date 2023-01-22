package com.diyorbek.roomdatabase_h3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diyorbek.roomdatabase_h3.database.FoodEntity
import com.diyorbek.roomdatabase_h3.databinding.ItemLayoutBinding
import com.diyorbek.roomdatabase_h3.util.toBitmap

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodVIewHolder>() {
    lateinit var onClick: (FoodEntity) -> Unit
    private var foodList = mutableListOf<FoodEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodVIewHolder {
        return FoodVIewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FoodVIewHolder, position: Int) {
        holder.bind(foodList[position])
    }

    override fun getItemCount(): Int = foodList.size

    inner class FoodVIewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(foodEntity: FoodEntity) {
            binding.apply {
                binding.img.setImageBitmap(foodEntity.img.toBitmap())
                binding.foodName.text = foodEntity.name
                binding.foodCountry.text = foodEntity.country
                binding.foodRating.text = foodEntity.rating.toString()
            }
            itemView.setOnClickListener {
                onClick(foodEntity)
            }
        }

    }

    fun setList(foodEntity: MutableList<FoodEntity>) {
        this.foodList = foodEntity
        notifyDataSetChanged()
    }
}