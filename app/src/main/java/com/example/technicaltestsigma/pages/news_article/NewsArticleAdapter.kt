package com.example.technicaltestsigma.pages.news_article

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.technicaltestsigma.WebViewActivity
import com.example.technicaltestsigma.databinding.CardSourcesBinding
import com.example.technicaltestsigma.model.model.article.Article
import com.example.technicaltestsigma.pages.main.MainActivityAdapter

class NewsArticleAdapter(
    private val context: Context,
    private val articles: List<Article>,
) : RecyclerView.Adapter<NewsArticleAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        return MainViewHolder(
            CardSourcesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val userList = articles[position]
        holder.bind(userList)
    }

    override fun getItemCount(): Int {
        Log.d("adapter size", articles.size.toString())
        return articles.size
    }


    inner class MainViewHolder(private val binding: CardSourcesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NewApi")
        fun bind(article: Article) {

            binding.apply {
                nameSources.text = article.source!!.name
                descSources.text = article.description
                categorySources.text = article.author
                title.visibility = View.VISIBLE
                title.text = article.title
                link.text = article.url

//                Linkify.addLinks(link, Linkify.WEB_URLS);
//                link.movementMethod = LinkMovementMethod.getInstance()
                link.setOnClickListener {
                    val intent = Intent(context, WebViewActivity::class.java)
                    val bundle = Bundle()
                    intent.putExtra("url", article.url)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    ContextCompat.startActivity(context, intent, bundle)
                }
            }

        }
    }
}