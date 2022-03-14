package com.example.pinterestappkotlin.model

import com.google.gson.annotations.SerializedName

data class MyProfile (
    val id: String? = null,
    val updated_at: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("name")
    val name: String? = null,
    val first_name: String? = null,
    val last_name: String? = null,
    val twitter_username: Any? = null,
    val portfolioURL: Any? = null,
    val bio: Any? = null,
    val location: Any? = null,
    val links: Links? = null,
    @SerializedName("profile_image")
    val profile_image: ProfileImage? = null,
    val instagram_username: Any? = null,
    val total_collections: Long? = null,
    val total_likes: Long? = null,
    val total_photos: Long? = null,
    val accepted_tos: Boolean? = null,
    val for_hire: Boolean? = null,
    val social: Social? = null,
    val followed_byUser: Boolean? = null,
    val photos: List<Any?>? = null,
    val badge: Any? = null,
    val tags: Tags? = null,
    @SerializedName("followers_count")
    val followers_count: Long? = null,
    @SerializedName("following_count")
    val following_count: Long? = null,
    val allow_messages: Boolean? = null,
    val numericID: Long? = null,
    val downloads: Long? = null,
    val meta: Meta? = null
)

data class Links (
    val self: String? = null,
    val html: String? = null,
    val photos: String? = null,
    val likes: String? = null,
    val portfolio: String? = null,
    val following: String? = null,
    val followers: String? = null
)

data class Meta (
    val index: Boolean? = null
)

data class Tags (
    val custom: List<Any?>? = null,
    val aggregated: List<Any?>? = null
)
