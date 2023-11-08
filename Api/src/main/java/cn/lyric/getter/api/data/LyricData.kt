package cn.lyric.getter.api.data

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import cn.lyric.getter.api.data.type.OperateType

@SuppressLint("ParcelClassLoader") class LyricData private constructor(parcel: Parcel) : Parcelable {

    /**
     * Type [OperateType] 歌词数据类型
     */
    var type: OperateType = OperateType.UPDATE

    /**
     * Lyric [String] 歌词
     */
    var lyric: String = ""

    /**
     * Extra data [ExtraData] 额外数据
     */
    var extraData: ExtraData = ExtraData()

    constructor() : this(Parcel.obtain())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.apply {
            writeInt(type.ordinal)
            writeString(lyric)
            val bundle = Bundle().apply {
                extraData.extra.forEach { (key, value) ->
                    when (value) {
                        is String -> putString(key, value)
                        is Int -> putInt(key, value)
                        is Boolean -> putBoolean(key, value)
                        is Float -> putFloat(key, value)
                        is Long -> putLong(key, value)
                        is Double -> putDouble(key, value)
                    }
                }
            }
            writeBundle(bundle)
        }
    }

    override fun describeContents(): Int = 0

    init {
        type = OperateType.values()[parcel.readInt()]
        lyric = parcel.readString() ?: ""
        extraData.extra = parcel.readBundle().let {
            val hashMap = HashMap<String, Any>()
            it?.keySet()?.forEach { key ->
                hashMap[key] = it.get(key) as Any
            }
            return@let hashMap
        }
    }

    override fun toString(): String {
        return "{\"type\":\"$type\",\"lyric\":\"$lyric\",\"extra\":\"$extraData\"}"
    }

    override fun equals(other: Any?): Boolean {
        if (other is LyricData) {
            return type == other.type && lyric == other.lyric
        }
        return false
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + lyric.hashCode()
        result = 31 * result + extraData.hashCode()
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
