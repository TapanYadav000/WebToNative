package com.tapan.webtonative.ui.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tapan.webtonative.utils.AppContainer
import com.tapan.webtonative.viewmodel.HistoryViewModel
import com.tapan.webtonative.viewmodel.HistoryViewModelFactory
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import java.util.Date
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme

@Composable
fun HistoryScreen(
    onWebsiteClick: (String) -> Unit
) {

    val context = LocalContext.current

    val viewModel: HistoryViewModel =
        viewModel(
            factory = HistoryViewModelFactory(
                AppContainer.provideRepository(context)
            )
        )

    val websites by
    viewModel.websiteHistory.collectAsState(
        initial = emptyList()
    )

    if (websites.isEmpty()) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "🌐",
                    style = MaterialTheme.typography.displayMedium
                )

                Text(
                    text = "No browsing history yet",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Visited websites will appear here",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    } else {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            items(websites) { website ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),

                    onClick = {
                        onWebsiteClick(website.url)
                    }
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),

                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = website.url,
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )

                        IconButton(
                            onClick = {
                                viewModel.deleteWebsite(
                                    website
                                )
                            }
                        ) {

                            Text("🗑")
                        }
                    }
                }
            }
        }
    }
}