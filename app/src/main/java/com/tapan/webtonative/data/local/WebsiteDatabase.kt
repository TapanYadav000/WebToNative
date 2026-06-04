package com.tapan.webtonative.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WebsiteEntity::class],
    version = 1
)
abstract class WebsiteDatabase : RoomDatabase() {

    abstract fun websiteDao(): WebsiteDao
}