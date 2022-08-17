package com.example.technicaltestsigma.pages.news_article

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.technicaltestsigma.R
import com.example.technicaltestsigma.databinding.ActivityNewsArticleBinding
import com.example.technicaltestsigma.pages.news_sources.NewsSourcesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsArticleBinding
    private lateinit var viewModel: NewsArticleViewModel
    private lateinit var newsArticleAdapter: NewsArticleAdapter
    private var source :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[NewsArticleViewModel::class.java]

        binding.apply {
            lifecycleOwner = this@NewsArticleActivity
            vm = viewModel
        }
        setupAdapter()
        retriveFromIntent()
        setUpObserver()

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = getString(R.string.news_article)

    }

    private fun setupAdapter(){
        viewModel.article.observe(this, Observer {
            newsArticleAdapter = NewsArticleAdapter(this,it)
            binding.rvNewsArticle.adapter =newsArticleAdapter
            newsArticleAdapter.notifyDataSetChanged()
        })
    }

    private fun setUpObserver(){
        viewModel.emptyArticle.observe(this, Observer {
            binding.noArticle.visibility = View.VISIBLE
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun retriveFromIntent(){
        source = intent.getStringExtra("source")
        viewModel.getArticleBySource(source.toString())
    }
}