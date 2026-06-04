package com.tapan.webtonative.ui.home

import android.R.attr.text
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun HomeScreen(
    onOpenWebsite: (String) -> Unit,
    onHistoryClick: () -> Unit,
    onLogoutClick: () -> Unit
) {

    var url by remember {
        mutableStateOf("")
    }
    val currentUser =
        FirebaseAuth.getInstance().currentUser

    val userName =
        currentUser?.displayName ?: "User"

    val userEmail =
        currentUser?.email ?: ""

    var isError by remember {
        mutableStateOf(false)
    }

    data class FeaturedWebsite(
        val name: String,
        val url: String
    )

    val featuredWebsites = listOf(
        FeaturedWebsite(
            "Google",
            "https://www.google.com"
        ),
        FeaturedWebsite(
            "GitHub",
            "https://github.com"
        ),
        FeaturedWebsite(
            "ChatGPT",
            "https://chatgpt.com"
        ),
        FeaturedWebsite(
            "YouTube",
            "https://youtube.com"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "WebToNative",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Hello, $userName",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = userEmail,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = url,
                    onValueChange = {
                        url = it
                        isError = false
                    },
                    isError = isError,
                    label = {
                        Text("Website URL")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                if (isError) {

                    Text(
                        text = "Please enter a website URL",
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {

                        if (url.isBlank()) {

                            isError = true
                            return@Button
                        }

                        val formattedUrl =
                            if (
                                url.startsWith("http://") ||
                                url.startsWith("https://")
                            ) {
                                url
                            } else {
                                "https://$url"
                            }

                        onOpenWebsite(formattedUrl)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Open Website")
                }

            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Featured Websites",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.height(200.dp)
        ) {

            items(featuredWebsites) { website ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp),

                    onClick = {

                        if (url.isNotBlank()) {

                            val formattedUrl =
                                if (
                                    url.startsWith("http://") ||
                                    url.startsWith("https://")
                                ) {
                                    url
                                } else {
                                    "https://$url"
                                }

                            onOpenWebsite(formattedUrl)
                        }
                    }
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = website.name,
                            style = MaterialTheme.typography.titleMedium
                        )


                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onHistoryClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("View History")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onLogoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Logout")
        }
    }
}