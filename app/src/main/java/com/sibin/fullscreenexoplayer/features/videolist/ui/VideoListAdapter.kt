package com.sibin.fullscreenexoplayer.features.videolist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.util.Util
import com.sibin.fullscreenexoplayer.R
import com.sibin.fullscreenexoplayer.databinding.ItemVideoBinding
import com.sibin.fullscreenexoplayer.features.videolist.VideoModel
import com.sibin.fullscreenexoplayer.util.ExoPlayerState
import com.sibin.fullscreenexoplayer.util.ExoPlayerViewAdapter
import java.security.AccessController.getContext

/**Created by Sibin E A on 18,September,2020 sibinea1@gmail.com **/

class VideoListAdapter(
    val context: Context,
    private var mValues: List<VideoModel>,
    private var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<VideoListAdapter.ViewHolder>(), ExoPlayerState {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListAdapter.ViewHolder {
        val binding: ItemVideoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context)
            , R.layout.item_video, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(holder: VideoListAdapter.ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onViewRecycled(holder: ViewHolder) {
        val position = holder.adapterPosition
        ExoPlayerViewAdapter.releaseRecycledPlayers(position)
        super.onViewRecycled(holder)
    }

    private fun getItem(position: Int): VideoModel {
        return mValues[position]
    }

    interface OnItemClickListener {
        fun onBookMarkClick(
            view: View?,
            position: Int,
            model: VideoModel
        )
    }

    inner class ViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: VideoModel) {
            binding.bookmark.setOnClickListener {
                mValues[adapterPosition].isBookMarked = if (model.isBookMarked == 1) 0 else 1
                reflectBookMark(model, binding)
                onItemClickListener.onBookMarkClick(it, adapterPosition, mValues[adapterPosition])
            }
            binding.apply {
                videoModel = model
                playerState = this@VideoListAdapter
                index = adapterPosition
                reflectBookMark(model, binding)
                executePendingBindings()
            }
        }

        private fun reflectBookMark(details: VideoModel, binding: ItemVideoBinding) {
            if (details.isBookMarked == 1) {
                binding.bookmark.setImageResource(R.drawable.ic_bookmark_solid)
                binding.bookmark.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.colorPrimary
                    )
                );
            } else {
                binding.bookmark.setImageResource(R.drawable.ic_bookmark_border)
                binding.bookmark.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.colorPrimary
                    )
                );
            }
        }
    }

    override fun onStartedPlaying(player: Player) {
    }

    override fun onFinishedPlaying(player: Player) {
    }
}