package com.example.newsapp.repository

import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.db.models.Article

class NewsRepository (val db: ArticleDatabase) {
    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    suspend fun delete(article: Article) = db.getArticleDao().deleteArticle(article)

    fun getAllNews() = db.getArticleDao().getAllArticles()
}
