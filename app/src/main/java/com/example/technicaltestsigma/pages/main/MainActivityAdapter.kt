package com.example.technicaltestsigma.pages.main

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technicaltestsigma.databinding.CardSourcesBinding
import com.example.technicaltestsigma.model.model.everything.Article

class MainActivityAdapter(
    private val context: Context,
    private val articles: List<Article>,
) : RecyclerView.Adapter<MainActivityAdapter.MainViewHolder>() {
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

                layoutSource.setOnClickListener {
//                    val intent = Intent(context, LoanConfirmationActivity::class.java)
//                    val bundle = Bundle()
//                    intent.putExtra("nominal", nominalPulsa.nominal)
//                    intent.putExtra("phone",phoneNumber)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    ContextCompat.startActivity(context, intent, bundle)
                }
            }

        }
    }
}