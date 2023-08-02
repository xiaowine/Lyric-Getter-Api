package cn.lyric.getter.api.data

import android.os.Parcel
import android.os.Parcelable

class LyricData : Parcelable {
    var type: DataType = DataType.UPDATE
    var lyric: String = ""
    var customIcon = false
    var serviceName: String = ""
    var packageName: String = ""
    var base64Icon: String = ""
    var useOwnMusicController = false
    var delay = 0

    constructor()

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(type.ordinal)
        dest.writeString(lyric)
        dest.writeInt(if (customIcon) 1 else 0)
        dest.writeString(serviceName)
        dest.writeString(packageName)
        dest.writeString(base64Icon)
        dest.writeInt(if (useOwnMusicController) 1 else 0)
        dest.writeInt(delay)
    }

    override fun describeContents(): Int {
        return 0
    }

    private constructor(parcel: Parcel) {
        type = DataType.values()[parcel.readInt()]
        lyric = parcel.readString() ?: ""
        customIcon = parcel.readInt() != 0
        serviceName = parcel.readString() ?: ""
        packageName = parcel.readString() ?: ""
        base64Icon = parcel.readString() ?: ""
        useOwnMusicController = parcel.readInt() != 0
        delay = parcel.readInt()
    }

    override fun toString(): String {
        return "{\"type\":\"$type\",\"lyric\":\"$lyric\",\"customIcon\":$customIcon,\"serviceName\":\"$serviceName\",\"packageName\":\"$packageName\",\"base64Icon\":\"$base64Icon\",\"useOwnMusicController\":\"$useOwnMusicController\",\"delay\":\"$delay\"}"
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
