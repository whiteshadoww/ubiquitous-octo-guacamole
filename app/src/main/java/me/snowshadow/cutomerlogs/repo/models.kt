package me.snowshadow.cutomerlogs.repo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CustomerRecord(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0)