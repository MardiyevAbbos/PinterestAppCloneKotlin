package com.example.pinterestappkotlin.model

import com.google.gson.annotations.SerializedName

data class MyProfile (
    val id: String? = null,
    val updatedAt: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("name")
    val name: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val twitterUsername: Any? = null,
    val portfolioURL: Any? = null,
    val bio: Any? = null,
    val location: Any? = null,
    val links: Links? = null,
    @SerializedName("profile_image")
    val profileImage: ProfileImage? = null,
    val instagramUsername: Any? = null,
    val totalCollections: Long? = null,
    val totalLikes: Long? = null,
    val totalPhotos: Long? = null,
    val acceptedTos: Boolean? = null,
    val forHire: Boolean? = null,
    val social: Social? = null,
    val followedByUser: Boolean? = null,
    val photos: List<Any?>? = null,
    val badge: Any? = null,
    val tags: Tags? = null,
    @SerializedName("followers_count")
    val followersCount: Long? = null,
    @SerializedName("following_count")
    val followingCount: Long? = null,
    val allowMessages: Boolean? = null,
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
