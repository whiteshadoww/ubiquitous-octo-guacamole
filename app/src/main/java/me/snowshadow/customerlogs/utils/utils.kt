package me.snowshadow.customerlogs.utils

import java.text.SimpleDateFormat
import java.util.*


fun Date.formatTime(): String? {
    val dateFormat = SimpleDateFormat("dd, MMM YYYY", Locale.getDefault())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return dateFormat.format(this) + " at " + timeFormat.format(this)

}