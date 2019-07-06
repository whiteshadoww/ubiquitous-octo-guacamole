package me.snowshadow.customerlogs.repo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.snowshadow.customerlogs.repo.CustomerRecord

@TypeConverters(DateConverter::class)
@Database(entities = [CustomerRecord::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun records(): RecordsDao
}