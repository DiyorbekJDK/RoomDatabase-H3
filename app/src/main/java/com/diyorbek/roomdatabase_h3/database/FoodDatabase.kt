package com.diyorbek.roomdatabase_h3.database

import android.content.Context
import androidx.room.*

@Database(entities = [FoodEntity::class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract val dao: FoodDao

    companion object {
        @Volatile
        private var instance: FoodDatabase? = null

        operator fun invoke(context: Context): FoodDatabase {
            return instance ?: synchronized(Any()) {
                instance ?: createDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun createDatabase(context: Context): FoodDatabase {
            return Room.databaseBuilder(
                context,
                FoodDatabase::class.java,
                "Food.db"
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }
    }
}