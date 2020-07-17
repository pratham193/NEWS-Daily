package com.example.news

import android.annotation.TargetApi
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build


object AppPermission {
    val isMarshmallowPlusDevice: Boolean
        get() = Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1

    @TargetApi(Build.VERSION_CODES.M)
    fun isPermissionRequestRequired(
        activity: Activity,
        permissions: Array<String>,
        requestCode: Int
    ): Boolean {
        if (isMarshmallowPlusDevice && permissions.size > 0) {
            val newPermissionList: MutableList<String> = ArrayList()
            for (permission in permissions) {
                if (PackageManager.PERMISSION_GRANTED != activity.checkSelfPermission(permission)) {
                    newPermissionList.add(permission)
                }
            }
            if (newPermissionList.size > 0) {
                activity.requestPermissions(
                    newPermissionList.toTypedArray(),
                    requestCode
                )
                return true
            }
        }
        return false
    }
}