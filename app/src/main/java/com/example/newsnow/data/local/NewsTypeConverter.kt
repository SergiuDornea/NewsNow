package com.example.newsnow.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsnow.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return "{$source.id}, ${source.name}"
    }

    @TypeConverter
    fun stringToSource(string: String): Source {
        return Source(string.split(",")[0], string.split(",")[1])
    }
}