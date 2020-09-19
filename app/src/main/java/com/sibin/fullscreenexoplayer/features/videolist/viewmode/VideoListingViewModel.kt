package com.sibin.fullscreenexoplayer.features.videolist.viewmode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibin.fullscreenexoplayer.base.UseCase
import com.sibin.fullscreenexoplayer.features.videolist.VideoModel
import com.sibin.fullscreenexoplayer.features.videolist.interactor.GetAllVideos
import com.sibin.fullscreenexoplayer.features.videolist.interactor.SetAsBookmarked
import javax.inject.Inject


/**Created by Sibin E A on 18,September,2020 sibinea1@gmail.com **/

class VideoListingViewModel @Inject constructor(
    private val getAllVideos: GetAllVideos,
    private val setAsBookmarked: SetAsBookmarked
) :
    ViewModel() {

    var allVideos: MutableLiveData<List<VideoModel>> = MutableLiveData()
    fun getAllVideos() {
        getAllVideos.invoke(viewModelScope, UseCase.None()) {
            allVideos.postValue(it.value)
        }
    }

    fun updateBookmark(videoModel: VideoModel) {
        setAsBookmarked.invoke(viewModelScope, videoModel) {
        }
    }

}
