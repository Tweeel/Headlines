package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import kotlinx.android.synthetic.main.fragment_article.*
import java.lang.reflect.InvocationTargetException

class ArticleFragment: Fragment(R.layout.fragment_article)  {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try{
            val args: ArticleFragmentArgs by navArgs()
            val article = args.article
            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }
        }catch (e: InvocationTargetException){
            Log.e("ArticleFragment", "Error: ${e.message}")
        }
    }
}