package me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines_pagination

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.anisjamadar.newsapp_mvvm_architecture.databinding.ActivityTopHeadlinesBinding
import javax.inject.Inject

@AndroidEntryPoint
class TopHeadlinesPaginationActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: TopHeadLinePaginationAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityTopHeadlinesBinding
    private lateinit var newsViewModel: TopHeadlinePaginationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopHeadlinesBinding.inflate(layoutInflater)
        newsViewModel = ViewModelProvider(this, viewModelFactory)[TopHeadlinePaginationViewModel::class]
        setContentView(binding.root)
        setUpList()
        setUpObservers()
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

    private fun setUpObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsViewModel.pagingDataFlow.collectLatest {
                    adapter.submitData(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow
                    .collectLatest { loadStates ->
                        when (val refreshState = loadStates.refresh) {
                            is LoadState.Error -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this@TopHeadlinesPaginationActivity, "Error: ${refreshState.error.message}", Toast.LENGTH_LONG)
                                    .show()
                            }
                            is LoadState.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                                binding.recyclerView.visibility = View.GONE
                            }
                            is LoadState.NotLoading -> {
                                binding.progressBar.visibility = View.GONE
                                binding.recyclerView.visibility = View.VISIBLE
                            }
                        }
                    }
            }
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, TopHeadlinesPaginationActivity::class.java)
        }
    }
}