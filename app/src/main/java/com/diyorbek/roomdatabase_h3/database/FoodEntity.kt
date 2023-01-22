package com.diyorbek.roomdatabase_h3.database

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "FoodTable")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val img: ByteArray,
    val name: String,
    val country: String,
    val rating: String
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FoodEntity

        if (id != other.id) return false
        if (!img.contentEquals(other.img)) return false
        if (name != other.name) return false
        if (country != other.country) return false
        if (rating != other.rating) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + img.contentHashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + country.hashCode()
        result = 31 * result + rating.hashCode()
        return result
    }
}
