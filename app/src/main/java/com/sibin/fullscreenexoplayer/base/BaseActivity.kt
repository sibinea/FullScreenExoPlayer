package com.sibin.fullscreenexoplayer.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sibin.fullscreenexoplayer.ExpApplication
import com.sibin.fullscreenexoplayer.R
import com.sibin.fullscreenexoplayer.di.ApplicationComponent

/**Created by Sibin E A on 18,September,2020 sibinea1@gmail.com **/
abstract class BaseActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as ExpApplication).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_main)
        addFragment(savedInstanceState)
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(
                R.id.fragmentContainer,
                fragment()
            )
        }

    abstract fun fragment(): BaseFragment

}