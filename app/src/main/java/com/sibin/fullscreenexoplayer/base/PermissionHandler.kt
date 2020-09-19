package com.sibin.fullscreenexoplayer.base

import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment


/**Created by Sibin E A on 18,September,2020 sibinea1@gmail.com **/

fun Fragment.isGranted(permission: AppPermission) = run {
    context?.let {
        (PermissionChecker.checkSelfPermission(
            it, permission.permissionName
        ) == PermissionChecker.PERMISSION_GRANTED)
    } ?: false
}

fun Fragment.requestPermission(permission: AppPermission) {
    requestPermissions(
        arrayOf(permission.permissionName), permission.requestCode
    )
}
