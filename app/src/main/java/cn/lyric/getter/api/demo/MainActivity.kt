package cn.lyric.getter.api.demo


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import cn.lyric.getter.api.LyricListener
import cn.lyric.getter.api.data.DataType
import cn.lyric.getter.api.data.LyricData
import cn.lyric.getter.api.tools.EventTools
import cn.lyric.getter.api.tools.Tools
import cn.lyric.getter.api.tools.Tools.drawableToBase64
import cn.lyric.getter.api.tools.Tools.registerLyricListener
import cn.lyric.getter.api.tools.Tools.unregisterLyricListener


class MainActivity : Activity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        检查激活状态
        findViewById<TextView>(R.id.activation).text = "激活状态：${EventTools.hasEnable}"
//        开始监听歌词
        findViewById<Button>(R.id.start).setOnClickListener {
            start()
        }
//        发送歌词的几种方式（不完全展示）
        findViewById<Button>(R.id.send).setOnClickListener {
            EventTools.sendLyric(applicationContext, "${(0..1000).random()}${getString(R.string.app_name)}", applicationContext.packageName)
        }
        findViewById<Button>(R.id.send2).setOnClickListener {
            EventTools.sendLyric(applicationContext, "${(0..1000).random()}${getString(R.string.app_name)}", true, drawableToBase64(getDrawable(R.drawable.ic_launcher_foreground)!!), false, "", applicationContext.packageName, 0)
        }
        findViewById<Button>(R.id.send3).setOnClickListener {
            EventTools.sendLyric(applicationContext, "${(0..1000).random()}${getString(R.string.app_name)}")
        }
        findViewById<Button>(R.id.clean).setOnClickListener {
            EventTools.stopLyric(applicationContext)
        }
//        取消监听
        findViewById<Button>(R.id.stop).setOnClickListener {
            unregisterLyricListener(applicationContext)
        }


    }

    @SuppressLint("SetTextI18n")
    fun start() {
        //        注册歌词监听器
        registerLyricListener(applicationContext, EventTools.API_VERSION, object : LyricListener() {
            override fun onUpdate(lyricData: LyricData) {
                findViewById<TextView>(R.id.lyric).text = "Lyric：${if (lyricData.type == DataType.UPDATE) lyricData.lyric else " 暂停播放 "}"
                findViewById<TextView>(R.id.type).text = "Type：${lyricData.type}"
                findViewById<TextView>(R.id.customIcon).text = "CustomIcon：${lyricData.customIcon}"
                findViewById<TextView>(R.id.serviceName).text = "ServiceName：${lyricData.serviceName}"
                findViewById<TextView>(R.id.packageName).text = "PackageName：${lyricData.packageName}"
                findViewById<TextView>(R.id.base64Icon).text = "Base64Icon：${lyricData.base64Icon}"
                findViewById<TextView>(R.id.useOwnMusicController).text = "useOwnMusicController：${lyricData.useOwnMusicController}"
                findViewById<TextView>(R.id.toString).text = "toString：$lyricData"
                if (lyricData.customIcon) {
                    findViewById<ImageView>(R.id.icon).setImageBitmap(Tools.base64ToDrawable(lyricData.base64Icon))
                } else {
                    findViewById<ImageView>(R.id.icon).setImageBitmap(null)
                }
            }

            override fun onStop(lyricData: LyricData) {
                Toast.makeText(applicationContext, "歌词停止播放", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        EventTools.stopLyric(applicationContext)
//        取消注册歌词监听器
        unregisterLyricListener(applicationContext)
        super.onDestroy()
    }
}