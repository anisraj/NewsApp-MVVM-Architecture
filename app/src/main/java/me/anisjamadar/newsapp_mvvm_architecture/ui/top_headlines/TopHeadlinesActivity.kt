package me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import me.anisjamadar.newsapp_mvvm_architecture.NewsApplication
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article
import me.anisjamadar.newsapp_mvvm_architecture.databinding.ActivityTopHeadlinesBinding
import me.anisjamadar.newsapp_mvvm_architecture.di.component.DaggerActivityComponent
import me.anisjamadar.newsapp_mvvm_architecture.di.module.ActivityModule
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.UiState
import javax.inject.Inject
import javax.inject.Provider

class TopHeadlinesActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    @Inject
    lateinit var newsViewModel: TopHeadlineViewModel

    private lateinit var binding: ActivityTopHeadlinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityTopHeadlinesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpList()
        setUpObserver()
    }

    private fun setUpList() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@TopHeadlinesActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(articleList: List<Article>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }
}