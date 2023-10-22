package cn.lyric.getter.api.tools

import android.annotation.SuppressLint
import android.content.Context
import cn.lyric.getter.api.BuildConfig
import cn.lyric.getter.api.data.ExtraData

@SuppressLint("StaticFieldLeak")
class LGA(private val context: Context) {
    /**
     * Has enable
     * 是否启用
     * @return 是否启用，普通环境下为false，被Hook后为true
     */
    val hasEnable = false


    /**
     * Send lyric
     * 发送歌词（全参数）
     *
     * @param lyric [String] 歌词
     * @param extra [HashMap] 额外参数，默认为null
     */
    fun sendLyric(lyric: String, extra: ExtraData? = null) {}


    /**
     * Clear lyric
     *
     */
    fun clearLyric() {}

    companion object {
        /**
         * Api Version
         */
        const val API_VERSION = BuildConfig.API_VERSION
    }
}
