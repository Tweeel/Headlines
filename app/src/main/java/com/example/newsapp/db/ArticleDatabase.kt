package com.example.newsapp.db

import android.content.Context
import androidx.room.*
import com.example.newsapp.db.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object{
        @Volatile
        private var instant: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instant ?: synchronized(LOCK){
            instant ?: createDatabase(context).also {
                instant = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}