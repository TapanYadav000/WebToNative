package com.tapan.webtonative.navigation
import android.net.Uri

sealed class Screen(val route: String) {

    object SignIn : Screen("signin")

    object Home : Screen("home")

    object History : Screen("history")

    object WebView : Screen("webview/{url}") {

        fun createRoute(url: String): String {
            return "webview/${Uri.encode(url)}"
        }
    }
}