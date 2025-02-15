package com.boooplay.model.provider

import kotlinx.serialization.Serializable
import java.security.MessageDigest
import kotlin.random.Random

/**
 * Represents the data associated with a provider.
 *
 * @property authors The list of [Author]s who contributed to the provider.
 * @property repositoryUrl The main repository URL of the provider, if available.
 * @property buildUrl The URL for downloading the provider build.
 * @property changelog The changelog of the provider, if available. Supports Markdown.
 * @property versionName The version name of the provider.
 * @property versionCode The version code of the provider.
 * @property adult Whether the provider is considered adult-only.
 * @property description The description of the provider. Supports Markdown.
 * @property iconUrl The URL to the icon/image associated with the provider, if available.
 * @property language The primary [Language] supported by this provider.
 * @property name The name of the provider.
 * @property providerType The [ProviderType] of the provider.
 * @property status The [Status] of the provider.
 * @property id The unique identifier for the provider as name alone is not reliable enough to determine he uniqueness of a provider.
 *
 * @see Status
 * @see Language
 * @see ProviderType
 * @see Author
 */
@Serializable
data class ProviderData(
    val authors: List<Author>,
    val repositoryUrl: String?,
    val buildUrl: String?,
    val changelog: String? = null,
    val versionName: String,
    val versionCode: Long,
    // ==================== \\
    val adult: Boolean = false,
    val description: String?,
    val iconUrl: String?,
    val language: Language,
    val name: String,
    val providerType: ProviderType,
    val status: Status,
) {
    val id = generateHash()

    private fun generateHash(): String {
        val uniqueHashCode = "${hashCode()}".toByteArray() + Random(System.currentTimeMillis()).nextBytes(30)

        val digest = MessageDigest.getInstance("SHA-1")
        val hashBytes = digest.digest(uniqueHashCode)
        return hashBytes.toHexString().take(15)
    }

    private fun ByteArray.toHexString(): String = joinToString("") { "%02x".format(it) }
}