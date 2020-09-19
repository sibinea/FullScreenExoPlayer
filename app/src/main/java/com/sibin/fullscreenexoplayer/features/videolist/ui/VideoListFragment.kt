package com.sibin.fullscreenexoplayer.features.videolist.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import com.sibin.fullscreenexoplayer.R
import com.sibin.fullscreenexoplayer.base.*
import com.sibin.fullscreenexoplayer.features.videolist.VideoModel
import com.sibin.fullscreenexoplayer.features.videolist.viewmode.VideoListingViewModel
import com.sibin.fullscreenexoplayer.util.ExoPlayerViewAdapter
import com.sibin.fullscreenexoplayer.util.RecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_video_list.*

class VideoListFragment : BaseFragment(), VideoListAdapter.OnItemClickListener {


    private lateinit var videoListingViewModel: VideoListingViewModel
    private var videoList = mutableListOf<VideoModel>()

    override fun layoutId() = R.layout.fragment_video_list
    private lateinit var scrollListener: RecyclerViewScrollListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        videoListingViewModel = viewModel(viewModelFactory) {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        videoListingViewModel.allVideos.observe(viewLifecycleOwner) {
            handleVideos(it)
        }

        getVideos()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        getVideos()
    }

    private fun getVideos() {
        if (isGranted(AppPermission.READ_EXTERNAL_STORAGE)) {
            videoListingViewModel.getAllVideos()
        } else {
            requestPermission(AppPermission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun setRecyclerView() {
        recycler_video.layoutManager = LinearLayoutManager(context)
        PagerSnapHelper().apply {
            this.attachToRecyclerView(recycler_video)
        }
        scrollListener = object : RecyclerViewScrollListener() {
            override fun onItemIsFirstVisibleItem(index: Int) {
                Log.d("visible item index", index.toString())
                if (index != -1)
                    ExoPlayerViewAdapter.playIndexThenPausePreviousPlayer(index)
            }

        }
        recycler_video.addOnScrollListener(scrollListener)
    }

    override fun onPause() {
        super.onPause()
        ExoPlayerViewAdapter.releaseAllPlayers()
    }

    private fun handleVideos(lists: List<VideoModel>?) {
        if (!lists.isNullOrEmpty()) {
            recycler_video.visible()
            error_view.invisible()
            videoList.addAll(lists)
        } else {
            error_view.visible()
            recycler_video.invisible()
        }
        val videoAdapter = VideoListAdapter(requireContext(), videoList, this)
        recycler_video.adapter = videoAdapter
    }

    override fun onBookMarkClick(view: View?, position: Int, model: VideoModel) {
        videoListingViewModel.updateBookmark(model)
    }

}
