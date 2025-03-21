package me.anisjamadar.domain.model

import com.google.gson.annotations.SerializedName
import me.anisjamadar.domain.model.Source

data class Article(
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val imageUrl: String?,
    @SerializedName("source")
    val source: Source?
)
