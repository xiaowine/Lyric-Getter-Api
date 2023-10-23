package cn.lyric.getter.api.tools

import android.content.Context
import cn.lyric.getter.api.API
import cn.lyric.getter.api.BuildConfig
import cn.lyric.getter.api.data.ExtraData

@Deprecated("已过时，请使用API类")
object EventTools {
    const val API_VERSION = BuildConfig.API_VERSION

    val hasEnable = false

    @Deprecated("已过时，请使用API类中的sendLyric方法", ReplaceWith("sendLyric(lyric: String, extra: ExtraData = ExtraData())", "cn.lyric.getter.api.Api.sendLyric"))
    fun sendLyric(context: Context, lyric: String) {
        sendLyric(context, lyric, false, "", false, "", context.packageName, 0, null)
    }

    @Deprecated("已过时，请使用API类中的sendLyric方法", ReplaceWith("sendLyric(lyric: String, extra: ExtraData = ExtraData())", "cn.lyric.getter.api.Api.sendLyric"))
    fun sendLyric(context: Context, lyric: String, delay: Int) {
        sendLyric(context, lyric, false, "", false, "", context.packageName, delay, null)
    }

    @Deprecated("已过时，请使用API类中的sendLyric方法", ReplaceWith("sendLyric(lyric: String, extra: ExtraData = ExtraData())", "cn.lyric.getter.api.Api.sendLyric"))
    fun sendLyric(context: Context, lyric: String, packageName: String) {
        sendLyric(context, lyric, false, "", false, "", packageName, 0, null)
    }

    @Deprecated("已过时，请使用API类中的sendLyric方法", ReplaceWith("sendLyric(lyric: String, extra: ExtraData = ExtraData())", "cn.lyric.getter.api.Api.sendLyric"))
    fun sendLyric(context: Context, lyric: String, packageName: String, delay: Int) {
        sendLyric(context, lyric, false, "", false, "", packageName, delay, null)
    }

    @Deprecated("已过时，请使用API类中的sendLyric方法", ReplaceWith("sendLyric(lyric: String, extra: ExtraData = ExtraData())", "cn.lyric.getter.api.Api.sendLyric"))
    fun sendLyric(context: Context, lyric: String, customIcon: Boolean, base64Icon: String, useOwnMusicController: Boolean, serviceName: String, packageName: String, delay: Int) {
        sendLyric(context, lyric, customIcon, base64Icon, useOwnMusicController, serviceName, packageName, delay, null)
    }

    @Deprecated("已过时，请使用API类中的sendLyric方法", ReplaceWith("sendLyric(lyric: String, extra: ExtraData = ExtraData())", "cn.lyric.getter.api.Api.sendLyric"))
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
             * @param extra                 额外数据
             */
    fun sendLyric(context: Context, lyric: String, customIcon: Boolean, base64Icon: String, useOwnMusicController: Boolean, serviceName: String, packageName: String, delay: Int, extra: HashMap<String, Any>?) {
        API(context).sendLyric(lyric, ExtraData(customIcon, base64Icon, useOwnMusicController, packageName, delay))
    }

    @Deprecated("已过时，clearLyric", ReplaceWith("clearLyric()", "cn.lyric.getter.api.Api.clearLyric"))
    fun stopLyric(context: Context) {
    }

}
