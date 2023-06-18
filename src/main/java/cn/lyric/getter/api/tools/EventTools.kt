package cn.lyric.getter.api.tools

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import cn.lyric.getter.api.LyricReceiver
import cn.lyric.getter.api.data.LyricData

object EventTools {
    fun hasEnable(): Boolean {
        return false
    }


    /**
     * 发送歌词
     *
     * @param context     Context
     * @param lyric       歌词
     * @param packageName 音乐包名
     */
    fun sendLyric(context: Context?, lyric: String?, packageName: String?) {
        sendLyric(context, lyric, false, "", false, "", packageName)
    }

    /**
     * 把歌词
     *
     * @param context               Context
     * @param lyric                 歌词
     * @param customIcon            是否传入自定义图标
     * @param base64Icon            Base64图标，仅在customIcon为true时生效
     * @param useOwnMusicController 音乐软件自己控制歌词暂停
     * @param serviceName           音乐服务名称，仅在useOwnMusicController为false时生效
     * @param packageName           音乐包名
     */
    fun sendLyric(context: Context?, lyric: String?, customIcon: Boolean, base64Icon: String?, useOwnMusicController: Boolean, serviceName: String?, packageName: String?) {}


    /**
     * 接待抒情
     * @param [context] Context
     * @param [callback] 收到歌词的回调
     */
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    fun receptionLyric(context: Context, callback: (LyricData) -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(LyricReceiver(callback), IntentFilter().apply {
                addAction("Lyric_Data")
            }, Context.RECEIVER_EXPORTED)
        } else {
            context.registerReceiver(LyricReceiver(callback), IntentFilter().apply {
                addAction("Lyric_Data")
            })
        }
    }
}
