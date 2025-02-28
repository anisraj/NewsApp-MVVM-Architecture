package me.anisjamadar.newsapp_mvvm_architecture.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import me.anisjamadar.newsapp_mvvm_architecture.R
import me.anisjamadar.newsapp_mvvm_architecture.databinding.ActivityMainBinding
import me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines.TopHeadlinesActivity
import me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines_pagination.TopHeadlinesPaginationActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topHeadlineButton.setOnClickListener {
            startActivity(TopHeadlinesActivity.createIntent(this))
        }

        binding.topHeadlinePaginationButton.setOnClickListener {
            startActivity(TopHeadlinesPaginationActivity.createIntent(this))
        }
    }
}