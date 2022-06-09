package com.onix.okucherenko.loginapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onix.okucherenko.loginapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)
    }
}