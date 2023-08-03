package cn.lyric.getter.api

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import cn.lyric.getter.api.data.LyricData


interface LyricListener {
    fun onReceived(lyricData: LyricData)
}

class LyricReceiver(private val lyricListener: LyricListener) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        runCatching {
            val lyricData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("Data", LyricData::class.java)!!
            } else {
                intent.getParcelableExtra("Data")!!
            }
            lyricListener.onReceived(lyricData)
        }
    }
}