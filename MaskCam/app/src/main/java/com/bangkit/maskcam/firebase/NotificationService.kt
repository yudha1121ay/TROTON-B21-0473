package com.bangkit.maskcam.firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class NotificationService : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        super.onTokenRefresh()

        val refreshToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Your Token $refreshToken")
    }
}