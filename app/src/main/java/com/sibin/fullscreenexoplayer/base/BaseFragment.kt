package com.sibin.fullscreenexoplayer.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sibin.fullscreenexoplayer.ExpApplication
import com.sibin.fullscreenexoplayer.di.ApplicationComponent
import javax.inject.Inject


/**Created by Sibin E A on 18,September,2020 sibinea1@gmail.com **/
abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as ExpApplication).appComponent
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(
        layoutId(), container,
        false
    )


}