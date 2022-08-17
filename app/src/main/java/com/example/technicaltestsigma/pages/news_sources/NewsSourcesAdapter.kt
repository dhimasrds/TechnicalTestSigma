package com.example.technicaltestsigma.pages.news_sources

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.technicaltestsigma.databinding.CardSourcesBinding
import com.example.technicaltestsigma.model.model.sources.Source
import com.example.technicaltestsigma.pages.news_article.NewsArticleActivity

class NewsSourcesAdapter(
    private val context: Context,
    private val sources :List<Source>,
) : RecyclerView.Adapter<NewsSourcesAdapter.SourcesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesViewHolder {
        return SourcesViewHolder(
            CardSourcesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SourcesViewHolder, position: Int) {
        val userList = sources[position]
        holder.bind(userList)
    }

    override fun getItemCount(): Int {
        Log.d("adapter size", sources.size.toString())
        return sources.size
    }


    inner class SourcesViewHolder(private val binding: CardSourcesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NewApi")
        fun bind(source: Source) {

            binding.apply {
                nameSources.text = source.name
                descSources.text = source.description
                categorySources.text = source.category
                layoutSource.setOnClickListener {
                    val intent = Intent(context, NewsArticleActivity::class.java)
                    val bundle = Bundle()
                    intent.putExtra("source",source.name)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    ContextCompat.startActivity(context, intent, bundle)
                }
            }

        }

    }

}