package com.tonimurr.marvel.common

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatString(pattern: String): String {
    return try{
        val dateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
        dateFormat.format(this)
    }catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}