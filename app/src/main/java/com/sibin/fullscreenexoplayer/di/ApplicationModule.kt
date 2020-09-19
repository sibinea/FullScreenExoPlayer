package com.sibin.fullscreenexoplayer.di

import android.content.Context
import com.sibin.fullscreenexoplayer.ExpApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/*
* Mention all the application level injections here*/
@Module
class ApplicationModule(private val application: ExpApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application


}