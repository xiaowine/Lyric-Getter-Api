package cn.lyric.getter.api.data

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable

@SuppressLint("ParcelClassLoader") class LyricData private constructor(parcel: Parcel) : Parcelable {
    var type: DataType = DataType.UPDATE
    var lyric: String = ""
    var customIcon = false
    var serviceName: String = ""
    var packageName: String = ""
    var base64Icon: String = ""
    var useOwnMusicController = false
    var delay = 0
    var extra: HashMap<String, Any>? = null


    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.apply {
            writeInt(type.ordinal)
            writeString(lyric)
            writeInt(if (customIcon) 1 else 0)
            writeString(serviceName)
            writeString(packageName)
            writeString(base64Icon)
            writeInt(if (useOwnMusicController) 1 else 0)
            writeInt(delay)
            val bundle = Bundle().apply {
                extra?.forEach {
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

    override fun describeContents(): Int {
        return 0
    }

    init {
        type = DataType.values()[parcel.readInt()]
        lyric = parcel.readString() ?: ""
        customIcon = parcel.readInt() != 0
        serviceName = parcel.readString() ?: ""
        packageName = parcel.readString() ?: ""
        base64Icon = parcel.readString() ?: ""
        useOwnMusicController = parcel.readInt() != 0
        delay = parcel.readInt()
        extra = parcel.readBundle().let {
            val hashMap = HashMap<String, Any>()
            it?.keySet()?.forEach { key ->
                hashMap[key] = it.get(key) as Any
            }
            return@let hashMap
        }
    }

    override fun toString(): String {
        return "{\"type\":\"$type\",\"lyric\":\"$lyric\",\"customIcon\":$customIcon,\"serviceName\":\"$serviceName\",\"packageName\":\"$packageName\",\"base64Icon\":\"$base64Icon\",\"useOwnMusicController\":\"$useOwnMusicController\",\"delay\":\"$delay\",\"extra\":\"$extra\"}"
    }

    override fun equals(other: Any?): Boolean {
        if (other is LyricData) {
            return type == other.type && lyric == other.lyric && customIcon == other.customIcon && serviceName == other.serviceName && packageName == other.packageName && base64Icon == other.base64Icon && useOwnMusicController == other.useOwnMusicController && delay == other.delay
        }
        return false
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + lyric.hashCode()
        result = 31 * result + customIcon.hashCode()
        result = 31 * result + serviceName.hashCode()
        result = 31 * result + packageName.hashCode()
        result = 31 * result + base64Icon.hashCode()
        result = 31 * result + useOwnMusicController.hashCode()
        result = 31 * result + delay
        result = 31 * result + extra.hashCode()
        return result
    }

    companion object CREATOR : Parcelable.Creator<LyricData> {
        override fun createFromParcel(parcel: Parcel): LyricData {
            return LyricData(parcel)
        }

        override fun newArray(size: Int): Array<LyricData?> {
            return arrayOfNulls(size)
        }
    }
}
