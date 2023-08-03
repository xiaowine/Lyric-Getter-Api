package cn.lyric.getter.api.tools

import android.content.Context
import cn.lyric.getter.api.BuildConfig

object EventTools {
    const val API_VERSION = BuildConfig.API_VERSION

    const val hasEnable = false

    fun sendLyric(context: Context, lyric: String) {
        sendLyric(context, lyric, false, "", false, "", context.packageName, 0)
    }

    fun sendLyric(context: Context, lyric: String, delay: Int) {
        sendLyric(context, lyric, false, "", false, "", context.packageName, delay)
    }

    fun sendLyric(context: Context, lyric: String, packageName: String) {
        sendLyric(context, lyric, false, "", false, "", packageName, 0)
    }

    fun sendLyric(context: Context, lyric: String, packageName: String, delay: Int) {
        sendLyric(context, lyric, false, "", false, "", packageName, delay)
    }


    /**
     * 发送歌词
     *
     * @param context               Context
     * @param lyric                 歌词
     * @param customIcon            是否传入自定义图标
     * @param base64Icon            Base64图标，仅在customIcon为true时生效
     * @param useOwnMusicController 音乐软件自己控制歌词暂停
     * @param serviceName           音乐服务名称，仅在useOwnMusicController为false时生效
     * @param packageName           音乐包名
     * @param delay                 歌词显示时间，单位s，默认0
     */
    fun sendLyric(context: Context, lyric: String, customIcon: Boolean, base64Icon: String, useOwnMusicController: Boolean, serviceName: String, packageName: String, delay: Int) {}

    fun stopLyric(context: Context) {}

}
