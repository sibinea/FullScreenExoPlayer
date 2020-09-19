package com.sibin.fullscreenexoplayer.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**Created by Sibin E A on 19,September,2020 sibinea1@gmail.com **/

abstract class RecyclerViewScrollListener : RecyclerView.OnScrollListener() {
    private var firstVisibleItem = 0
    private var visibleItemCount = 0


    @Volatile
    private var mEnabled = true
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (mEnabled) {
            val manager = recyclerView.layoutManager
            require(manager is LinearLayoutManager) { "Expected recyclerview to have linear layout manager" }
            visibleItemCount = manager.childCount
            firstVisibleItem = manager.findFirstCompletelyVisibleItemPosition()
            onItemIsFirstVisibleItem(firstVisibleItem)
        }
    }

    abstract fun onItemIsFirstVisibleItem(index: Int)
}