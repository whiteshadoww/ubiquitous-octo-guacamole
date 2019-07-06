package me.snowshadow.customerlogs.repo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CustomerRecord(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0)