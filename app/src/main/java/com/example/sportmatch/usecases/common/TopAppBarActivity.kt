package com.example.sportmatch.usecases.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sportmatch.R

class TopAppBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_app_bar)
    }
}