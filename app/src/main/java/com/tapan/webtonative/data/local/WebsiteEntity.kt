package com.tapan.webtonative.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "website_history")
data class WebsiteEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val url: String,

    val timestamp: Long
)