package com.example.newsapp.db

import androidx.room.TypeConverter
import com.example.newsapp.db.models.Source

class Converters {
    @TypeConverter
    fun fromSource(srouce: Source): String {
        return srouce.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}