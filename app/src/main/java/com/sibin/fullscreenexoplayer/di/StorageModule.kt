package com.sibin.fullscreenexoplayer.di

import android.content.Context
import androidx.room.Room
import com.sibin.fullscreenexoplayer.base.ExoDatabase
import com.sibin.fullscreenexoplayer.features.videolist.data.VideoModelDao
import com.sibin.fullscreenexoplayer.features.videolist.data.VideoStorageRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**Created by Sibin E A on 19,September,2020 sibinea1@gmail.com **/
@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ExoDatabase {
        return Room.databaseBuilder(
            context,
            ExoDatabase::class.java,
            ExoDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideDao(database: ExoDatabase): VideoModelDao {
        return database.videoModelDoa()
    }

    @Provides
    @Singleton
    fun provideStorageRepository(source: VideoStorageRepository.Database)
            : VideoStorageRepository = source

}