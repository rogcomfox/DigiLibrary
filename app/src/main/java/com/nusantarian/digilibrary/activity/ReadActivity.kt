package com.nusantarian.digilibrary.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nusantarian.digilibrary.databinding.ActivityReadBinding

class ReadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Read Activity"
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null){
            val data = extras.getString("titles")
            val url = "$data.pdf"
            binding.pdfView.fromAsset(url).load()
        }
    }
}
