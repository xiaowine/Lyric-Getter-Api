package cn.lyric.getter.api.tools

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.util.Base64
import cn.lyric.getter.api.LyricListener
import cn.lyric.getter.api.LyricReceiver
import java.io.ByteArrayOutputStream

@SuppressLint("StaticFieldLeak")
object Tools {
    private lateinit var lyricReceiver: LyricReceiver


    /**
     *
     * @param [base64] 图片的Base64
     * @return [Bitmap] 返回图片的Bitmap?，传入Base64无法转换则为null
     */
    fun base64ToDrawable(base64: String): Bitmap? {
        return try {
            val bitmapArray: ByteArray = Base64.decode(base64, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * 将Drawable转换成Base64
     *
     * @param drawable 图片
     * @return [String] 返回图片的Base64
     */
    fun drawableToBase64(drawable: Drawable): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (drawable is AdaptiveIconDrawable) {
                return adaptiveIconDrawableBase64(drawable)
            }
        }
        when (drawable) {
            is BitmapDrawable -> {
                return drawableToBase64(drawable.bitmap)
            }

            is VectorDrawable -> {
                return drawableToBase64(makeDrawableToBitmap(drawable))
            }

            else -> {
                return try {
                    drawableToBase64((drawable as BitmapDrawable).bitmap)
                } catch (_: Exception) {
                    ""
                }
            }
        }
    }

    /**
     * 将自适应图标转换为位图
     *
     * @param drawable
     * @return [String] 返回自适应图的Base64
     */
    private fun adaptiveIconDrawableBase64(drawable: AdaptiveIconDrawable): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val background = drawable.background
            val foreground = drawable.foreground
            if (background != null && foreground != null) {
                val layerDrawable = LayerDrawable(arrayOf(background, foreground))
                val createBitmap = Bitmap.createBitmap(layerDrawable.intrinsicWidth, layerDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(createBitmap)
                layerDrawable.setBounds(0, 0, canvas.width, canvas.height)
                layerDrawable.draw(canvas)
                drawableToBase64(createBitmap)
            } else {
                ""
            }
        } else {
            ""
        }
    }

    /**
     * 将Bitmap转换成Base64
     *
     * @param bitmap 图片
     * @return [String] 返回图片的Base64
     */
    fun drawableToBase64(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val bytes = stream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    private fun makeDrawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.apply {
            setBounds(0, 0, canvas.width, canvas.height)
            draw(canvas)
        }
        return bitmap
    }


    /**
     * 注册歌词监听器
     * @param [context] Context
     * @param [apiVersion] 当前Api版本
     * @param [lyricListener] LyricListener
     */
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    fun registerLyricListener(context: Context, apiVersion: Int, lyricListener: LyricListener) {
        if (apiVersion != LGA.API_VERSION) return
        val intentFilter = IntentFilter().apply { addAction("Lyric_Data") }
        lyricReceiver = LyricReceiver(lyricListener)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(lyricReceiver, intentFilter, Context.RECEIVER_EXPORTED)
        } else {
            context.registerReceiver(lyricReceiver, intentFilter)
        }
    }

    /**
     * 注销歌词监听器
     *
     */
    fun unregisterLyricListener(context: Context) {
        if (!::lyricReceiver.isInitialized) return
        runCatching { context.unregisterReceiver(lyricReceiver) }
    }

}