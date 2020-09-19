package com.sibin.fullscreenexoplayer.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sibin.fullscreenexoplayer.base.ExoDatabase.Companion.DATABASE_VERSION
import com.sibin.fullscreenexoplayer.features.videolist.VideoModel
import com.sibin.fullscreenexoplayer.features.videolist.data.VideoModelDao


/**Created by Sibin E A on 19,September,2020 sibinea1@gmail.com **/

@Database(
    entities = [VideoModel::class], version = DATABASE_VERSION, exportSchema = false
)

abstract class ExoDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "Trell"
        const val DATABASE_VERSION = 1
    }

    abstract fun videoModelDoa(): VideoModelDao

}