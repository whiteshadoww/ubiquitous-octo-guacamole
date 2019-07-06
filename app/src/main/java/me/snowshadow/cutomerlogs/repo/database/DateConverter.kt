package me.snowshadow.cutomerlogs.repo.database

import androidx.room.TypeConverter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    private val out = "yyyy-MM-dd'T'HH:mm:ssZZZZZ"
    @TypeConverter
    fun toDate(timestamp: String?): Date? = if (timestamp == null) null else {

        val dateFormat = SimpleDateFormat(out, Locale.getDefault())

        try {
            dateFormat.parse(timestamp)
        } catch (e: NullPointerException) {
            null
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): String? {

        val dateFormat = SimpleDateFormat(out, Locale.getDefault())

        return try {
            dateFormat.format(date)
        } catch (e: NullPointerException) {
            null
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

}