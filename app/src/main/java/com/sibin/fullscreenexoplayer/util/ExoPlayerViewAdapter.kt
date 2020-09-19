package com.sibin.fullscreenexoplayer.util

import android.net.Uri
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory


/**Created by Sibin E A on 19,September,2020 sibinea1@gmail.com **/
class ExoPlayerViewAdapter {

    companion object {

        private var playersMap: MutableMap<Int, SimpleExoPlayer> = mutableMapOf()
        private var currentPlayingVideo: Pair<Int, SimpleExoPlayer>? = null

        fun releaseAllPlayers() {
            playersMap.map {
                it.value.release()
            }
        }

        fun releaseRecycledPlayers(index: Int) {
            playersMap[index]?.release()
        }

        private fun pauseCurrentPlayingVideo() {
            if (currentPlayingVideo != null) {
                currentPlayingVideo?.second?.playWhenReady = false
            }
        }

        fun playIndexThenPausePreviousPlayer(index: Int) {
            if (playersMap[index]?.playWhenReady == false) {
                pauseCurrentPlayingVideo()
                playersMap[index]?.playWhenReady = true
                currentPlayingVideo = Pair(index, playersMap.get(index)!!)
            }

        }


        @JvmStatic
        @BindingAdapter(value = ["video_url", "player_state", "item_index"], requireAll = false)
        fun PlayerView.loadVideo(url: String, callback: ExoPlayerState, item_index: Int? = null) {
            val player = SimpleExoPlayer.Builder(context).build()
            player.apply {
                playWhenReady = false
                repeatMode = Player.REPEAT_MODE_ALL
                setKeepContentOnPlayerReset(true)
                useController = false
            }
            val mediaSource =
                ProgressiveMediaSource.Factory(DefaultDataSourceFactory(context, "Sibin"))
                    .createMediaSource(Uri.parse(url))
            player.prepare(mediaSource)
            this.player = player

            if (playersMap.containsKey(item_index))
                playersMap.remove(item_index)

            if (item_index != null)
                playersMap[item_index] = player

            this.player?.addListener(object : Player.EventListener {
                override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                    super.onPlayerStateChanged(playWhenReady, playbackState)

                    if (playbackState == Player.STATE_READY && player.playWhenReady) {
                        callback.onStartedPlaying(player)
                    }
                }
            })
        }
    }
}
