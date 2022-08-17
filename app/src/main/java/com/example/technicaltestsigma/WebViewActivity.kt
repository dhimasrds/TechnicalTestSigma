package com.example.technicaltestsigma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.technicaltestsigma.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWebViewBinding
    private var url : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_web_view)
        retriveFromIntent()
        binding.apply {
            lifecycleOwner = this@WebViewActivity
            webview.loadUrl(url.toString())
        }

    }

    private fun retriveFromIntent(){
        url =intent.getStringExtra("url")
    }
}