package com.sibin.fullscreenexoplayer.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sibin.fullscreenexoplayer.features.videolist.viewmode.VideoListingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/*
* ViewModels will mention here
**/
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(VideoListingViewModel::class)
    abstract fun bindsVideoListingViewModel(videoListingViewModel: VideoListingViewModel): ViewModel
}