package cn.lyric.getter.api.demo


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import cn.lyric.getter.api.listener.LyricListener
import cn.lyric.getter.api.data.OperateType
import cn.lyric.getter.api.data.LyricData
import cn.lyric.getter.api.API
import cn.lyric.getter.api.data.ExtraData
import cn.lyric.getter.api.listener.LyricReceiver
import cn.lyric.getter.api.tools.Tools
import cn.lyric.getter.api.tools.Tools.registerLyricListener
import cn.lyric.getter.api.tools.Tools.unregisterLyricListener


class MainActivity : Activity() {
    private val lga by lazy { API() }
    private lateinit var receiver: LyricReceiver

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        检查激活状态
        findViewById<TextView>(R.id.activation).text = "激活状态：${lga.hasEnable}"
//        开始监听歌词
        findViewById<Button>(R.id.start).setOnClickListener {
            start()
        }
        findViewById<Button>(R.id.send).setOnClickListener {
            lga.sendLyric("${(0..1000).random()}${getString(R.string.app_name)}", extra = ExtraData().apply {
                packageName = "cn.lyric.getter.api.demo"
                customIcon = true
                base64Icon = Tools.drawableToBase64(getDrawable(R.mipmap.ic_launcher)!!)
                useOwnMusicController = false
                delay = 0
            })
        }
        findViewById<Button>(R.id.clean).setOnClickListener {
            lga.clearLyric()
        }
//        取消监听
        findViewById<Button>(R.id.stop).setOnClickListener {
            unregisterLyricListener(applicationContext, receiver)
        }


    }

    @SuppressLint("SetTextI18n")
    fun start() {
        //        注册歌词监听器
        receiver = LyricReceiver(object : LyricListener() {
            override fun onUpdate(lyricData: LyricData) {
                findViewById<TextView>(R.id.lyric).text = "Lyric：${if (lyricData.type == OperateType.UPDATE) lyricData.lyric else " 暂停播放 "}"
                findViewById<TextView>(R.id.type).text = "Type：${lyricData.type}"
                findViewById<TextView>(R.id.customIcon).text = "CustomIcon：${lyricData.extraData.extra}"
                findViewById<TextView>(R.id.packageName).text = "PackageName：${lyricData.extraData.packageName}"
                findViewById<TextView>(R.id.base64Icon).text = "Base64Icon：${lyricData.extraData.base64Icon}"
                findViewById<TextView>(R.id.useOwnMusicController).text = "useOwnMusicController：${lyricData.extraData.useOwnMusicController}"
                findViewById<TextView>(R.id.toString).text = "toString：$lyricData"
                if (lyricData.extraData.customIcon) {
                    findViewById<ImageView>(R.id.icon).setImageBitmap(Tools.base64ToDrawable(lyricData.extraData.base64Icon))
                } else {
                    findViewById<ImageView>(R.id.icon).setImageBitmap(null)
                }
            }

            override fun onStop(lyricData: LyricData) {
                Toast.makeText(applicationContext, "歌词停止播放", Toast.LENGTH_SHORT).show()
            }
        })
        registerLyricListener(applicationContext, API.API_VERSION, receiver)
    }

    override fun onDestroy() {
        lga.clearLyric()
//        取消注册歌词监听器
        unregisterLyricListener(applicationContext, receiver)
        super.onDestroy()
    }
}