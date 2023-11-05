package cn.lyric.getter.api.data

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable

class ExtraData private constructor(parcel: Parcel) : Parcelable {
    constructor() : this(Parcel.obtain())

    constructor(customIcon: Boolean, base64Icon: String, useOwnMusicController: Boolean, packageName: String, delay: Int) : this() {
        extra["customIcon"] = customIcon
        extra["base64Icon"] = base64Icon
        extra["useOwnMusicController"] = useOwnMusicController
        extra["packageName"] = packageName
        extra["delay"] = delay
    }

    var extra: HashMap<String, Any> = HashMap()
    var base64Icon: String
        get() = getString("base64Icon", "")
        set(value) = setString("base64Icon", value)
    var customIcon: Boolean
        get() = getBoolean("customIcon", false)
        set(value) = setBoolean("customIcon", value)
    var useOwnMusicController: Boolean
        get() = getBoolean("useOwnMusicController", false)
        set(value) = setBoolean("useOwnMusicController", value)
    var packageName: String
        get() = getString("packageName", "")
        set(value) = setString("packageName", value)
    var delay: Int
        get() = getInt("delay", 0)
        set(value) = setInt("delay", value)


    fun getString(key: String, default: String): String {
        return (extra[key] ?: default).toString()
    }

    fun getBoolean(key: String, default: Boolean): Boolean {
        return (extra[key] ?: default) as Boolean
    }

    fun getInt(key: String, default: Int): Int {
        return (extra[key] ?: default) as Int
    }

    fun getFloat(key: String, default: Float): Float {
        return (extra[key] ?: default) as Float
    }

    fun getLong(key: String, default: Long): Long {
        return (extra[key] ?: default) as Long
    }

    fun getDouble(key: String, default: Double): Double {
        return (extra[key] ?: default) as Double
    }

    fun setString(key: String, value: String) {
        extra[key] = value
    }

    fun setBoolean(key: String, value: Boolean) {
        extra[key] = value
    }

    fun setInt(key: String, value: Int) {
        extra[key] = value
    }

    fun setFloat(key: String, value: Float) {
        extra[key] = value
    }

    fun setLong(key: String, value: Long) {
        extra[key] = value
    }

    fun setDouble(key: String, value: Double) {
        extra[key] = value
    }

    fun mergeExtra(other: ExtraData) {
        extra.putAll(other.extra)
    }

    fun mergeExtra(other: HashMap<String, Any>) {
        extra.putAll(other)
    }


    override fun toString(): String {
        val str: StringBuilder = StringBuilder()
        extra.forEach { str.append("${it.key}=${it.value}") }
        return str.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.apply {
            val bundle = Bundle().apply {
                extra.forEach {
                    when (it.value) {
                        BaseType.String -> putString(it.key, it.value as String)
                        BaseType.Int -> putInt(it.key, it.value as Int)
                        BaseType.Boolean -> putBoolean(it.key, it.value as Boolean)
                        BaseType.Float -> putFloat(it.key, it.value as Float)
                        BaseType.Long -> putLong(it.key, it.value as Long)
                        BaseType.Double -> putDouble(it.key, it.value as Double)
                    }
                }
            }
            writeBundle(bundle)
        }
    }

    override fun hashCode(): Int {
        return extra.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ExtraData
        return extra == other.extra
    }

    companion object CREATOR : Parcelable.Creator<ExtraData> {
        override fun createFromParcel(parcel: Parcel): ExtraData {
            return ExtraData(parcel)
        }

        override fun newArray(size: Int): Array<ExtraData?> {
            return arrayOfNulls(size)
        }
    }
}