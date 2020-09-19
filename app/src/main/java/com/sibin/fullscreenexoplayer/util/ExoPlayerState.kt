package com.sibin.fullscreenexoplayer.util

import com.google.android.exoplayer2.Player


/**Created by Sibin E A on 19,September,2020 sibinea1@gmail.com **/
interface ExoPlayerState {
    fun onStartedPlaying(player: Player)

    fun onFinishedPlaying(player: Player)
}