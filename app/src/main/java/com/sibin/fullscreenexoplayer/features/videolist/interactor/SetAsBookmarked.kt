package com.sibin.fullscreenexoplayer.features.videolist.interactor

import com.sibin.fullscreenexoplayer.base.UseCase
import com.sibin.fullscreenexoplayer.features.videolist.VideoModel
import com.sibin.fullscreenexoplayer.features.videolist.data.VideoStorageRepository
import javax.inject.Inject

/**Created by Sibin E A on 19,September,2020 sibinea1@gmail.com **/

class SetAsBookmarked @Inject constructor(
    private val videoStorageRepository: VideoStorageRepository
) :
    UseCase<Boolean, VideoModel>() {
    override suspend fun run(params: VideoModel): Boolean {
        return videoStorageRepository.setBookmarked(params)
    }
}