package com.example.technicaltestsigma.pages.news_sources

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.technicaltestsigma.R
import com.example.technicaltestsigma.api.ApiParam
import com.example.technicaltestsigma.databinding.ActivityNewsSourcesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsSourcesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsSourcesBinding
    private lateinit var viewModel: NewsSourcesViewModel
    private lateinit var newsSourcesAdapter: NewsSourcesAdapter
    private var category :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsSourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =ViewModelProvider(this).get(NewsSourcesViewModel::class.java)

        binding.apply {
            lifecycleOwner = this@NewsSourcesActivity
            vm = viewModel
        }
        retriveFromIntent()
        viewModel.getSourcesByCategory(category.toString())
        setUpObserver()
        setupAdapter()
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = getString(R.string.news_sources)
    }

    private fun setupAdapter(){
        viewModel.sources.observe(this, Observer {
            newsSourcesAdapter = NewsSourcesAdapter(this,it)
            binding.rvNewsSources.adapter =newsSourcesAdapter
            newsSourcesAdapter.notifyDataSetChanged()
        })
    }

    private fun setUpObserver(){
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it,Toast.LENGTH_LONG).show()
        })
    }

    private fun retriveFromIntent(){
        category = intent.getStringExtra(ApiParam.CATEGORY)
    }
}