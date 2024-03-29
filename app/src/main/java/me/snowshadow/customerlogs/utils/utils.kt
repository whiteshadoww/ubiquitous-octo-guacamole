package me.snowshadow.customerlogs.utils

import android.view.View
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*


fun Date.formatTime(): String? {
    val dateFormat = SimpleDateFormat("dd, MMM YYYY", Locale.getDefault())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return dateFormat.format(this) + " at " + timeFormat.format(this)
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

fun MaterialButton.hide() {

    visibility = View.GONE
}

fun TextInputEditText.disable() {
    isEnabled = false
}