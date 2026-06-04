package com.tapan.webtonative.utils

import android.content.Context
import com.tapan.webtonative.data.local.DatabaseProvider
import com.tapan.webtonative.data.repository.WebsiteRepository

object AppContainer {

    fun provideRepository(
        context: Context
    ): WebsiteRepository {

        val dao =
            DatabaseProvider
                .getDatabase(context)
                .websiteDao()

        return WebsiteRepository(dao)
    }
}