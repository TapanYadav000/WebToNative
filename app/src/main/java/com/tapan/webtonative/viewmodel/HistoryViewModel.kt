package com.tapan.webtonative.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapan.webtonative.data.local.WebsiteEntity
import com.tapan.webtonative.data.repository.WebsiteRepository
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: WebsiteRepository
) : ViewModel() {

    val websiteHistory =
        repository.getAllWebsites()

    fun deleteWebsite(
        website: WebsiteEntity
    ) {

        viewModelScope.launch {

            repository.deleteWebsite(
                website
            )
        }
    }
    fun saveWebsite(
        url: String
    ) {

        viewModelScope.launch {

            repository.insertWebsite(
                WebsiteEntity(
                    url = url,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }
}