package com.tapan.webtonative.utils

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

object GoogleSignInUtils {

    fun getGoogleSignInClient(
        context: Context
    ) = GoogleSignIn.getClient(
        context,
        GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )
            .requestIdToken(Constants.WEB_CLIENT_ID)
            .requestEmail()
            .build()
    )
}