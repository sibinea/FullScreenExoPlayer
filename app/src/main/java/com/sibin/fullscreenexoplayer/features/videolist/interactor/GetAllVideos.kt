package com.sibin.fullscreenexoplayer.features.videolist.interactor

import androidx.lifecycle.LiveData
import com.sibin.fullscreenexoplayer.base.UseCase
import com.sibin.fullscreenexoplayer.features.videolist.VideoModel
import com.sibin.fullscreenexoplayer.features.videolist.data.GetVideoRepository
import javax.inject.Inject

/**Created by Sibin E A on 19,September,2020 sibinea1@gmail.com **/

class GetAllVideos @Inject constructor(
    private val getVideoRepository: GetVideoRepository
) :
    UseCase<LiveData<List<VideoModel>>, UseCase.None>() {
    override suspend fun run(params: None): LiveData<List<VideoModel>> {
        return getVideoRepository.getAllVideos()
    }
}