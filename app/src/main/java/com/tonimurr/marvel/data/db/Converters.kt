package com.tonimurr.marvel.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tonimurr.marvel.data.model.DateDTO
import com.tonimurr.marvel.data.model.ImageDTO
import com.tonimurr.marvel.data.model.ResourceDTO
import com.tonimurr.marvel.data.model.TextObjectDTO
import com.tonimurr.marvel.data.model.UrlDTO

class Converters {

    @TypeConverter
    fun fromStringToListTextObjects(value: String?): List<TextObjectDTO> {
        if(value == null){
            return emptyList()
        }
        val listType = object : TypeToken<List<TextObjectDTO>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringToListUrls(value: String?): List<UrlDTO> {
        if(value == null){
            return emptyList()
        }
        val listType = object : TypeToken<List<UrlDTO>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringToDates(value: String?): List<DateDTO> {
        if(value == null){
            return emptyList()
        }
        val listType = object : TypeToken<List<DateDTO>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringToImages(value: String?): List<ImageDTO> {
        if(value == null){
            return emptyList()
        }
        val listType = object : TypeToken<List<ImageDTO>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListToString(list: List<Any>?): String {
        return Gson().toJson(list)
    }

}