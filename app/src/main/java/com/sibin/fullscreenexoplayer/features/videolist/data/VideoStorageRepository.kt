package com.sibin.fullscreenexoplayer.features.videolist.data

import com.sibin.fullscreenexoplayer.features.videolist.VideoModel
import javax.inject.Inject


/**Created by Sibin E A on 19,September,2020 sibinea1@gmail.com **/
interface VideoStorageRepository {

    suspend fun insertVideos(videos: List<VideoModel>): Boolean
    suspend fun setBookmarked(model: VideoModel): Boolean
    suspend fun getAllVideos(): List<VideoModel>
    suspend fun deleteAll(): Boolean

    class Database
    @Inject constructor(private val videoModelDao: VideoModelDao) :
        VideoStorageRepository {

        override suspend fun insertVideos(videos: List<VideoModel>): Boolean {
            videoModelDao.insertAll(videos)
            return true
        }

        override suspend fun setBookmarked(model: VideoModel): Boolean {
            videoModelDao.update(model)
            return true
        }

        override suspend fun getAllVideos(): List<VideoModel> {
            return videoModelDao.getAllVideos()
        }

        override suspend fun deleteAll(): Boolean {
            videoModelDao.deleteAll()
            return true
        }
    }
}