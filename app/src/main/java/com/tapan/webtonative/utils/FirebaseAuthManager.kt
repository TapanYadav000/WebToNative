package com.tapan.webtonative.utils

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthManager {

    val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun logout() {
        auth.signOut()
    }
}