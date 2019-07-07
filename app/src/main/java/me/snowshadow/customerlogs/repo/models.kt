package me.snowshadow.customerlogs.repo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
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
)