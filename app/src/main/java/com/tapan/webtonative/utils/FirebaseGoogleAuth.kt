package com.tapan.webtonative.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

object FirebaseGoogleAuth {

    fun firebaseAuthWithGoogle(
        idToken: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val credential =
            GoogleAuthProvider.getCredential(
                idToken,
                null
            )

        FirebaseAuth.getInstance()
            .signInWithCredential(credential)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(
                        task.exception ?: Exception()
                    )
                }
            }
    }
}