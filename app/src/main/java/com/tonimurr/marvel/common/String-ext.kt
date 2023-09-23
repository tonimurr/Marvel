package com.tonimurr.marvel.common

import java.lang.Exception
import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toMd5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}

fun String.toDate(pattern: String): Date? {
    return try{
        val dateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
        dateFormat.parse(this)
    }catch (e: Exception) {
        e.printStackTrace()
        null
    }
}