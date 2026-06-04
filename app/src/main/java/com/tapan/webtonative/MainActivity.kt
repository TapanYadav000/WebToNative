package com.tapan.webtonative

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tapan.webtonative.navigation.NavGraph
import com.tapan.webtonative.ui.theme.WebToNativeTheme
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.tapan.webtonative.navigation.Screen
import com.tapan.webtonative.utils.FirebaseGoogleAuth
import com.tapan.webtonative.utils.GoogleSignInUtils


class MainActivity : ComponentActivity() {
    private lateinit var signInLauncher:
            ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        Log.d(
            "FirebaseTest",
            "User = ${FirebaseAuth.getInstance().currentUser}"
        )

        signInLauncher =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
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

                            Log.d(
                                "FirebaseLogin",
                                "Login Successful"
                            )
                            println("FIREBASE LOGIN SUCCESS")
                        },
                        onFailure = {

                            Log.e(
                                "FirebaseLogin",
                                "Login Failed",
                                it
                            )
                            println("FIREBASE LOGIN FAILED")
                            it.printStackTrace()
                        }
                    )

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        setContent {
            WebToNativeTheme {
                NavGraph()
            }
        }
    }
}