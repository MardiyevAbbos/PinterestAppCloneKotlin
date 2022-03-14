package com.example.pinterestappkotlin.model

import com.google.gson.annotations.SerializedName

data class MyCollection(
    var title: String,
    var image: String
)

class Welcome: ArrayList<WelcomeElement>()

data class WelcomeElement (
    val id: String? = null,
    @SerializedName("title")
    val title: String? = null,
    val description: String? = null,
    val published_at: String? = null,
    val last_collected_at: String? = null,
    val updated_at: String? = null,
    val curated: Boolean? = null,
    val featured: Boolean? = null,
    val total_photos: Long? = null,
    val private: Boolean? = null,
    val share_key: String? = null,
    val tags: List<Tag>? = null,
    val links: WelcomeLinks? = null,
    val user: User? = null,
    @SerializedName("cover_photo")
    val cover_photo: WelcomeCoverPhoto? = null,
    val preview_photos: List<PreviewPhoto>? = null
)

data class WelcomeCoverPhoto (
    val id: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val promoted_at: String? = null,
    val width: Long? = null,
    val height: Long? = null,
    val color: String? = null,
    val blur_hash: String? = null,
    val description: String? = null,
    val alt_description: String? = null,
    val urls: Urls? = null,
    val links: CoverPhotoLinks? = null,
    val categories: List<Any?>? = null,
    val likes: Long? = null,
    val liked_byUser: Boolean? = null,
    val current_user_collections: List<Any?>? = null,
    val sponsorship: Any? = null,
    val topic_submissions: PurpleTopicSubmissions? = null,
    val user: User? = null
)

data class CoverPhotoLinks (
    val self: String? = null,
    val html: String? = null,
    val download: String? = null,
    val download_location: String? = null
)

data class PurpleTopicSubmissions (
    val food_drink: The3_DRenders? = null,
    val fashion: The3_DRenders? = null,
    val business_work: The3_DRenders? = null,
    val the3_d_renders: The3_DRenders? = null
)

data class The3_DRenders (
    val status: Status? = null,
    val approved_on: String? = null
)

enum class Status {
    Approved
}

data class PreviewPhoto (
    val id: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val blur_hash: String? = null,
    val urls: Urls? = null
)


data class SourceCoverPhoto (
    val id: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val promoted_at: String? = null,
    val width: Long? = null,
    val height: Long? = null,
    val color: String? = null,
    val blur_hash: String? = null,
    val description: String? = null,
    val alt_description: String? = null,
    val urls: Urls? = null,
    val links: CoverPhotoLinks? = null,
    val categories: List<Any?>? = null,
    val likes: Long? = null,
    val liked_byUser: Boolean? = null,
    val current_user_collections: List<Any?>? = null,
    val sponsorship: Any? = null,
    val topic_submissions: FluffyTopicSubmissions? = null,
    val user: User? = null
)

data class FluffyTopicSubmissions (
    val food_drink: The3_DRenders? = null,
    val health: The3_DRenders? = null,
    val textures_patterns: The3_DRenders? = null,
    val wallpapers: The3_DRenders? = null,
    val architecture: The3_DRenders? = null,
    val nature: The3_DRenders? = null,
    val spirituality: The3_DRenders? = null
)


