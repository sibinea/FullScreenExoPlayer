package com.sibin.fullscreenexoplayer.di

import com.sibin.fullscreenexoplayer.ExpApplication
import com.sibin.fullscreenexoplayer.base.BaseActivity
import com.sibin.fullscreenexoplayer.base.BaseFragment
import com.sibin.fullscreenexoplayer.di.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

/*
* Mention all the modules here. App level inject do here*/
@Singleton
@Component(
    modules = [ApplicationModule::class, ViewModelModule::class, RepositoryModule::class, StorageModule::class]
)
interface ApplicationComponent {
    fun inject(application: ExpApplication)
    fun inject(baseActivity: BaseActivity)
    fun inject(baseFragment: BaseFragment)
}