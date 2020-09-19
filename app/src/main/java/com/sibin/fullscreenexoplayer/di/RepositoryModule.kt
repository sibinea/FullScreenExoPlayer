package com.sibin.fullscreenexoplayer.di

import com.sibin.fullscreenexoplayer.features.videolist.data.GetVideoRepository
import dagger.Module
import dagger.Provides


/**Created by Sibin E A on 18,September,2020 sibinea1@gmail.com **/
@Module
class RepositoryModule() {

    @Provides
    fun provideMainRepository(
        dataSource: GetVideoRepository.Data
    ): GetVideoRepository = dataSource

}