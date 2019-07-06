package me.snowshadow.cutomerlogs.repo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.snowshadow.cutomerlogs.repo.CustomerRecord

@TypeConverters(DateConverter::class)
@Database(entities = [CustomerRecord::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun records(): RecordsDao
}