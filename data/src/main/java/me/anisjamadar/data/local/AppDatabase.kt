package me.anisjamadar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.anisjamadar.data.local.dao.ArticleDao
import me.anisjamadar.data.local.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}