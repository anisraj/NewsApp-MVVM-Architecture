package me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.anisjamadar.newsapp_mvvm_architecture.R
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article
import me.anisjamadar.newsapp_mvvm_architecture.databinding.LayoutHeadlineItemBinding
import javax.inject.Inject

class TopHeadlineAdapter @Inject constructor() : RecyclerView.Adapter<TopHeadlineAdapter.DataViewHolder>() {

    private val articleList: ArrayList<Article> = arrayListOf()

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutHeadlineItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(articleList[position])

    fun addData(list: List<Article>) {
        articleList.addAll(list)
    }

}