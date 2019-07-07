package me.snowshadow.customerlogs.repo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Entity
@Parcelize
data class CustomerRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var firstName: String,
    var lastName: String,
    var idNo: String,
    var qrData: String,
    var lat: Double,
    var lng: Double,
    var photo: String,
    var createdOn: Date
) : Parcelable