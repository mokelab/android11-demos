package com.mokelab.demo.android11.exitreason

import android.app.ApplicationExitInfo

fun toReasonLabel(reason: Int) = when (reason) {
    ApplicationExitInfo.REASON_ANR -> "REASON_ANR"
    ApplicationExitInfo.REASON_CRASH -> "REASON_CRASH"
    ApplicationExitInfo.REASON_CRASH_NATIVE -> "REASON_CRASH_NATIVE"
    ApplicationExitInfo.REASON_DEPENDENCY_DIED -> "REASON_DEPENDENCY_DIED"
    ApplicationExitInfo.REASON_EXCESSIVE_RESOURCE_USAGE -> "REASON_EXCESSIVE_RESOURCE_USAGE"
    ApplicationExitInfo.REASON_EXIT_SELF -> "REASON_EXIT_SELF"
    ApplicationExitInfo.REASON_INITIALIZATION_FAILURE -> "REASON_INITIALIZATION_FAILURE"
    ApplicationExitInfo.REASON_LOW_MEMORY -> "REASON_LOW_MEMORY"
    ApplicationExitInfo.REASON_OTHER -> "REASON_OTHER"
    ApplicationExitInfo.REASON_PERMISSION_CHANGE -> "REASON_PERMISSION_CHANGE"
    ApplicationExitInfo.REASON_SIGNALED -> "REASON_SIGNALED"
    ApplicationExitInfo.REASON_UNKNOWN -> "REASON_UNKNOWN"
    ApplicationExitInfo.REASON_USER_REQUESTED -> "REASON_USER_REQUESTED"
    ApplicationExitInfo.REASON_USER_STOPPED -> "REASON_USER_STOPPED"
    else -> ""
}