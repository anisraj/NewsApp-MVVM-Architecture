package me.anisjamadar.domain.model

import com.google.gson.annotations.SerializedName
import me.anisjamadar.domain.model.Article

data class TopHeadlinesResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0,
    @SerializedName("articles")
    val articles: List<Article> = ArrayList(),
)
