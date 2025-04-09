package me.anisjamadar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import me.anisjamadar.data.local.entity.ArticleEntity

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun flowOfArticles(): Flow<List<ArticleEntity>>

    @Insert
    fun insertAll(articles: List<ArticleEntity>)

    @Query("DELETE FROM articles")
    fun deleteAll()

    @Transaction
    suspend fun deleteAllAndUpdate(articles: List<ArticleEntity>) {
        deleteAll()
        insertAll(articles)
    }

}
