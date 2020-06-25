package com.mokelab.demo.android11.exitreason

import android.app.ActivityManager
import android.app.Application
import android.app.ApplicationExitInfo
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

class ExitReasonViewModel(private val app: Application) : AndroidViewModel(app) {
    val reasons: LiveData<List<ApplicationExitInfo>> = liveData {
        val manager = app.getSystemService(ActivityManager::class.java)
        val info = manager.getHistoricalProcessExitReasons(app.packageName, 0, 0)
        emit(info)
    }
}