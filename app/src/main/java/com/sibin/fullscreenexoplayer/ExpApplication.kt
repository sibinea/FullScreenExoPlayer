package com.sibin.fullscreenexoplayer

import android.app.Application
import com.sibin.fullscreenexoplayer.di.ApplicationComponent
import com.sibin.fullscreenexoplayer.di.ApplicationModule
import com.sibin.fullscreenexoplayer.di.DaggerApplicationComponent


/**Created by Sibin E A on 18,September,2020 sibinea1@gmail.com **/
class ExpApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent.builder().applicationModule(
            ApplicationModule(
                this
            )
        ).build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)
}