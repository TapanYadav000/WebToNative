package com.tapan.webtonative.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: WebsiteDatabase? = null

    fun getDatabase(
        context: Context
    ): WebsiteDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance =
                Room.databaseBuilder(
                    context.applicationContext,
                    WebsiteDatabase::class.java,
                    "website_database"
                ).build()

            INSTANCE = instance

            instance
        }
    }
}