package com.sibin.fullscreenexoplayer.features.videolist

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey


/**Created by Sibin E A on 18,September,2020 sibinea1@gmail.com **/
@Entity(tableName = "VIDEO_TABLE")
data class VideoModel(
    @PrimaryKey val id: Int,
    val videoUrl: String,
    var isBookMarked: Int = 0
)