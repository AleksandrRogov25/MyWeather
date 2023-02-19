package com.example.myweather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myweather.R
import com.example.myweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}