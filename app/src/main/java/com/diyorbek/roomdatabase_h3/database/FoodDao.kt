package com.diyorbek.roomdatabase_h3.database

import androidx.room.*

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveFood(foodEntity: FoodEntity)

    @Query("SELECT * FROM FoodTable ORDER BY id DESC")
    fun getAllFoods(): List<FoodEntity>

    @Update
    fun updateFood(foodEntity: FoodEntity)

    @Delete
    fun deleteFood(foodEntity: FoodEntity)
}