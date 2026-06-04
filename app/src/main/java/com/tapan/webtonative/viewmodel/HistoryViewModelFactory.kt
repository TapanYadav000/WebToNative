package com.tapan.webtonative.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tapan.webtonative.data.repository.WebsiteRepository

class HistoryViewModelFactory(
    private val repository: WebsiteRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return HistoryViewModel(
            repository
        ) as T
    }
}