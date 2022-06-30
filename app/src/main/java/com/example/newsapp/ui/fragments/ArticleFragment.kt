package com.example.newsapp.ui.fragments

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.ui.NewsActivity
import com.example.newsapp.viewModel.NewsViewModel
import com.example.newsapp.viewModel.NewsViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*
import java.lang.reflect.InvocationTargetException

class ArticleFragment: Fragment(R.layout.fragment_article)  {

    lateinit var viewModel: NewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsRepository = NewsRepository(ArticleDatabase(context!!))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]

        val myFabSrc = resources.getDrawable(R.drawable.ic_favorite)
        myFabSrc.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
        fab.setImageDrawable(myFabSrc)

        try{
            val args: ArticleFragmentArgs by navArgs()
            val article = args.article
            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }
            fab.setOnClickListener {
                viewModel.saveArticle(article)
                Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_LONG).show()
            }
        }catch (e: InvocationTargetException){
            Log.e("ArticleFragment", "Error: ${e.message}")
        }


    }
}