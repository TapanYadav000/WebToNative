package com.tapan.webtonative.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tapan.webtonative.ui.history.HistoryScreen
import com.tapan.webtonative.ui.home.HomeScreen
import com.tapan.webtonative.ui.signin.SignInScreen
import com.tapan.webtonative.ui.webview.WebViewScreen
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

import com.tapan.webtonative.utils.AppContainer
import com.tapan.webtonative.utils.FirebaseAuthManager
import com.tapan.webtonative.utils.GoogleSignInUtils
import com.tapan.webtonative.viewmodel.HistoryViewModel
import com.tapan.webtonative.viewmodel.HistoryViewModelFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.tapan.webtonative.utils.FirebaseGoogleAuth

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->

            val task =
                GoogleSignIn.getSignedInAccountFromIntent(
                    result.data
                )

            try {

                val account =
                    task.getResult(ApiException::class.java)

                FirebaseGoogleAuth.firebaseAuthWithGoogle(
                    account.idToken!!,
                    onSuccess = {
                        navController.navigate(Screen.Home.route)
                    },
                    onFailure = {
                        println("Login Failed")
                    }
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    val historyViewModel: HistoryViewModel =
        viewModel(
            factory = HistoryViewModelFactory(
                AppContainer.provideRepository(context)
            )
        )

    NavHost(
        navController = navController,
        startDestination =
            if (FirebaseAuthManager.isUserLoggedIn())
                Screen.Home.route
            else
                Screen.SignIn.route
    ) {

        composable(Screen.SignIn.route) {

            SignInScreen(
                onGoogleSignInClick = {

                    val signInIntent =
                        GoogleSignInUtils
                            .getGoogleSignInClient(context)
                            .signInIntent

                    launcher.launch(signInIntent)
                }
            )
        }

        composable(Screen.Home.route) {

            HomeScreen(
                onOpenWebsite = { url ->

                    historyViewModel.saveWebsite(url)

                    navController.navigate(
                        Screen.WebView.createRoute(url)
                    )
                },
                onHistoryClick = {
                    println("History clicked")
                    navController.navigate(Screen.History.route)
                },
                onLogoutClick = {
                    FirebaseAuthManager.logout()
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(0)
                    }
                }
            )
        }

        composable(
            route = Screen.WebView.route
        ) { backStackEntry ->

            val url = Uri.decode(
                backStackEntry.arguments?.getString("url") ?: ""
            )

            WebViewScreen(url)
        }

        composable(Screen.History.route) {

            HistoryScreen(
                onWebsiteClick = { url ->

                    navController.navigate(
                        Screen.WebView.createRoute(url)
                    )
                }
            )
        }
    }
}