package tech.ntic.data.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "profile")
class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email : String?,
    val password : String?,
    val status:Boolean? = false
) : Parcelable