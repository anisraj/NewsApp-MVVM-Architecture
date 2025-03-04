package me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines_pagination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.scopes.ActivityScoped
import me.anisjamadar.newsapp_mvvm_architecture.R
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article
import me.anisjamadar.newsapp_mvvm_architecture.databinding.LayoutHeadlineItemBinding
import javax.inject.Inject

@ActivityScoped
class TopHeadLinePaginationAdapter @Inject constructor()
    : PagingDataAdapter<Article, TopHeadLinePaginationAdapter.DataViewHolder>(COMPARATOR) {

    class DataViewHolder(private val binding: LayoutHeadlineItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(article: Article) {
                binding.tvTitle.text = article.title
                binding.tvDescription.text = article.description
                binding.tvSource.text = article.source.name
                Glide.with(binding.ivNewsHeadLines.context)
                    .load(article.imageUrl)
                    .error(R.drawable.drawable_news_background)
                    .into(binding.ivNewsHeadLines)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutHeadlineItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}