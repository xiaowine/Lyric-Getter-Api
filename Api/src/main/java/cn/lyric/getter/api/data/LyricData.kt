package cn.lyric.getter.api.data

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable

@SuppressLint("ParcelClassLoader") class LyricData private constructor(parcel: Parcel) : Parcelable {
    var type: OperateType = OperateType.UPDATE
    var lyric: String = ""
    var extraData: ExtraData = ExtraData()

    constructor() : this(Parcel.obtain())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.apply {
            writeInt(type.ordinal)
            writeString(lyric)
            val bundle = Bundle().apply {
                extraData.extra.forEach {
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
