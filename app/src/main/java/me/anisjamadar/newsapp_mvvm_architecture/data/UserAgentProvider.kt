package me.anisjamadar.newsapp_mvvm_architecture.data

class UserAgentProvider(
    private val versionName: String,
    private val applicationId: String
) {

    fun getUserAgent(): String {
        return "$applicationId/$versionName"
    }
}