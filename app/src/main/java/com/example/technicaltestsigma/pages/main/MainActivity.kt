package com.example.technicaltestsigma.pages.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.technicaltestsigma.R
import com.example.technicaltestsigma.api.ApiParam
import com.example.technicaltestsigma.databinding.ActivityMainBinding
import com.example.technicaltestsigma.pages.news_sources.NewsSourcesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var mainActivityAdapter: MainActivityAdapter
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
        }

        progressBar = binding.progressCircular

        searchNews()
        setUpAdapter()
        setUpObrserver()
        goToTargetActivity()
    }


    fun goToTargetActivity() {
        binding.apply {
            cardBusiness.setOnClickListener {
                val intent = Intent(this@MainActivity, NewsSourcesActivity::class.java)
                intent.putExtra(ApiParam.CATEGORY, ApiParam.BUSINESS)
                startActivity(intent)
            }
            entertainment.setOnClickListener {
                val intent = Intent(this@MainActivity, NewsSourcesActivity::class.java)
                intent.putExtra(ApiParam.CATEGORY, ApiParam.ENTERTAINMENT)
                startActivity(intent)
            }
            general.setOnClickListener {
                val intent = Intent(this@MainActivity, NewsSourcesActivity::class.java)
                intent.putExtra(ApiParam.CATEGORY, ApiParam.GENERAL)
                startActivity(intent)
            }
            sport.setOnClickListener {
                val intent = Intent(this@MainActivity, NewsSourcesActivity::class.java)
                intent.putExtra(ApiParam.CATEGORY, ApiParam.SPORT)
                startActivity(intent)
            }
            technology.setOnClickListener {
                val intent = Intent(this@MainActivity, NewsSourcesActivity::class.java)
                intent.putExtra(ApiParam.CATEGORY, ApiParam.TECHNOLOGY)
                startActivity(intent)
            }
            health.setOnClickListener {
                val intent = Intent(this@MainActivity, NewsSourcesActivity::class.java)
                intent.putExtra(ApiParam.CATEGORY, ApiParam.HEALTH)
                startActivity(intent)
            }
        }
    }

    fun setUpAdapter() {
        viewModel.everything.observe(this, Observer {
            mainActivityAdapter = MainActivityAdapter(this, it)
            binding.rvEverything.adapter = mainActivityAdapter
            mainActivityAdapter.notifyDataSetChanged()
        })
    }

    fun setUpObrserver() {
        viewModel.progressBar.observe(this, Observer {
            if (it) {
                progressBar.visibility = View.GONE
            }
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    fun searchNews() {
        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                progressBar.visibility = View.VISIBLE
                viewModel.getEverything(query.toString())
                binding.layoutCategory.visibility = View.GONE
                binding.rvEverything.visibility = View.VISIBLE
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    binding.layoutCategory.visibility = View.VISIBLE
                    binding.rvEverything.visibility = View.GONE
                }
                return false
            }

        })
    }
}