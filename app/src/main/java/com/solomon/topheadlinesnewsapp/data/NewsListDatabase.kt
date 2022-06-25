package com.solomon.topheadlinesnewsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsList::class], version = 1)
abstract class NewsListDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
