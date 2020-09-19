package com.sibin.fullscreenexoplayer.base

import android.Manifest
import com.sibin.fullscreenexoplayer.R


/**Created by Sibin E A on 18,September,2020 sibinea1@gmail.com **/
sealed class AppPermission (
    val permissionName: String, val requestCode: Int, val deniedMessageId: Int, val explanationMessageId: Int
) {
    companion object {
        val permissions: List<AppPermission> by lazy {
            listOf(
                READ_EXTERNAL_STORAGE
            )
        }
    }

    object READ_EXTERNAL_STORAGE : AppPermission(
        Manifest.permission.READ_EXTERNAL_STORAGE, 50,
        R.string.permission_required_text, R.string.permission_required_text
    )

}