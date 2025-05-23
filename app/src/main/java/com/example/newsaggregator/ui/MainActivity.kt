package com.example.newsaggregator.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newsaggregator.databinding.ActivityMainBinding
import com.example.newsaggregator.ui.screens.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModels()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        setOnApplyWindowInsetsListener()

    }


    private fun setOnApplyWindowInsetsListener() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val ime = insets.getInsets(WindowInsetsCompat.Type.ime()) // keyboard
            val bottom = if (ime.bottom > 0) ime.bottom else systemBars.bottom
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, bottom)
            insets
        }
    }
}
