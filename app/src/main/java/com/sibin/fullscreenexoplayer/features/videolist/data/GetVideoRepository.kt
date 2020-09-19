package com.sibin.fullscreenexoplayer.features.videolist.data

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sibin.fullscreenexoplayer.features.videolist.VideoModel
import javax.inject.Inject


/**Created by Sibin E A on 18,September,2020 sibinea1@gmail.com **/
interface GetVideoRepository {

    suspend fun getAllVideos(): LiveData<List<VideoModel>>

    class Data @Inject constructor(
        private val context: Context,
        private val videoStorageRepository: VideoStorageRepository
    ) : GetVideoRepository {
        override suspend fun getAllVideos(): LiveData<List<VideoModel>> {
            val allVideos: MutableLiveData<List<VideoModel>> = MutableLiveData()
            val videoList: MutableList<VideoModel> = mutableListOf()
            val contentResolver: ContentResolver = context.contentResolver
            val uri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
            var i = 0
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    val videoUri =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                    val videoModel = VideoModel(i++, videoUrl = videoUri)
                    videoList.add(videoModel);
                } while (cursor.moveToNext());
            }
            videoStorageRepository.insertVideos(videoList)
            allVideos.postValue(videoStorageRepository.getAllVideos())
            return allVideos
        }
    }
}