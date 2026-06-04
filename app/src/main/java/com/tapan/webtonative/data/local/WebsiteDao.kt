package com.tapan.webtonative.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WebsiteDao {

    @Delete
    suspend fun deleteWebsite(
        website: WebsiteEntity
    )
    @Insert
    suspend fun insertWebsite(
        website: WebsiteEntity
    )

    @Query(
        "SELECT * FROM website_history ORDER BY timestamp DESC"
    )
    fun getAllWebsites():
            Flow<List<WebsiteEntity>>
}