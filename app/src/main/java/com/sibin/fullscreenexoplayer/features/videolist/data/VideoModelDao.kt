package com.sibin.fullscreenexoplayer.features.videolist.data

import androidx.room.*
import com.sibin.fullscreenexoplayer.features.videolist.VideoModel


/**Created by Sibin E A on 19,September,2020 sibinea1@gmail.com **/
@Dao
interface VideoModelDao {
    companion object {
        private const val GET_ALL_VIDEOS = "SELECT * FROM VIDEO_TABLE ORDER BY isBookMarked DESC"
        private const val DELETE_ALL = "DELETE FROM VIDEO_TABLE"
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(model: VideoModel)

    @Transaction
    suspend fun insertAll(models: List<VideoModel>) {
        models.forEach {
            insert(it)
        }
    }

    @Update
    suspend fun update(model: VideoModel)

    @Query(GET_ALL_VIDEOS)
    suspend fun getAllVideos(): List<VideoModel>

    @Query(DELETE_ALL)
    suspend fun deleteAll()
}