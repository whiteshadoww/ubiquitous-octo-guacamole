package me.snowshadow.customerlogs.repo.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import me.snowshadow.customerlogs.repo.CustomerRecord

@Dao
interface RecordsDao {

    @Insert
    fun insertRecord(customerRecord: CustomerRecord)

    @get:Query("SELECT * FROM CustomerRecord ORDER BY createdOn DESC")
    val allRecords: DataSource.Factory<Int, CustomerRecord>
}