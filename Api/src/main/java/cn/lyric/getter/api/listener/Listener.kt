package cn.lyric.getter.api.listener

import cn.lyric.getter.api.data.LyricData

interface Listener {
    fun onReceived(lyricData: LyricData)
    fun onUpdate(lyricData: LyricData)
    fun onStop(lyricData: LyricData)
}