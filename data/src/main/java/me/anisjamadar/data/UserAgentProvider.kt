package me.anisjamadar.data

class UserAgentProvider(
    private val versionName: String,
    private val applicationId: String
) {

    fun getUserAgent(): String {
        return "$applicationId/$versionName"
    }
}