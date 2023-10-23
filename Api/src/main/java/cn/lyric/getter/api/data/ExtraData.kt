package cn.lyric.getter.api.data

class ExtraData() {
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

    constructor(customIcon: Boolean, base64Icon: String, useOwnMusicController: Boolean, packageName: String, delay: Int) : this() {
        extra["customIcon"] = customIcon
        extra["base64Icon"] = base64Icon
        extra["useOwnMusicController"] = useOwnMusicController
        extra["packageName"] = packageName
        extra["delay"] = delay
    }

    override fun toString(): String {
        val str: StringBuilder = StringBuilder()
        extra.forEach { str.append("${it.key}=${it.value}") }
        return str.toString()
    }
}