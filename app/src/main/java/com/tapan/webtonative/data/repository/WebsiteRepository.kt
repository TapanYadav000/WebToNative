package com.tapan.webtonative.data.repository


import com.tapan.webtonative.data.local.WebsiteDao
import com.tapan.webtonative.data.local.WebsiteEntity

class WebsiteRepository(
    private val websiteDao: WebsiteDao
) {

    suspend fun deleteWebsite(
        website: WebsiteEntity
    ) {
        websiteDao.deleteWebsite(website)
    }
    suspend fun insertWebsite(
        website: WebsiteEntity
    ) {
        websiteDao.insertWebsite(website)
    }

    fun getAllWebsites() =
        websiteDao.getAllWebsites()
}