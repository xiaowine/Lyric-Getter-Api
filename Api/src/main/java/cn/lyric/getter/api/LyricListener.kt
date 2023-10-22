package cn.lyric.getter.api

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import cn.lyric.getter.api.data.OperateType
import cn.lyric.getter.api.data.LyricData
import cn.lyric.getter.api.listener.Listener

abstract class LyricListener : Listener {
    /**
     * 歌词事件收到时调用
     *
     * @param lyricData
     */
    @Deprecated("Use onUpdate and onStop instead")
    override fun onReceived(lyricData: LyricData) {
    }

    /**
     * 歌词更新时调用
     * 歌词更新时，lyricData.lyric 不为空
     * 歌词更新时，lyricData.type 为 DataType.UPDATE
     *
     * @param lyricData
     */
    override fun onUpdate(lyricData: LyricData) {
    }

    /**
     * 歌词停止时调用
     * 歌词停止时，lyricData.lyric 为空
     * 歌词停止时，lyricData.type 为 DataType.STOP
     *
     * @param lyricData
     */
    override fun onStop(lyricData: LyricData) {
    }

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

            when (lyricData.type) {
                OperateType.UPDATE -> lyricListener.onUpdate(lyricData)
                OperateType.STOP -> lyricListener.onStop(lyricData)
            }
        }
    }
}