package com.example.pinterestappkotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PhotoList : ArrayList<PhotoItem>(), Serializable

data class PhotoItem(
    val id: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val promotedAt: String? = null,
    val width: Long? = null,
    val height: Long? = null,
    val color: String? = null,
    val blurHash: String? = null,
    @SerializedName("description")
    val description: String? = null,
    val altDescription: String? = null,
    val urls: Urls? = null,
    val links: WelcomeLinks? = null,
    val categories: List<Any?>? = null,
    @SerializedName("likes")
    val likes: Long? = null,
    val likedByUser: Boolean? = null,
    val currentUserCollections: List<Any?>? = null,
    @SerializedName("sponsorship")
    val sponsorship: Sponsorship? = null,
    val topicSubmissions: TopicSubmissions? = null,
    @SerializedName("user")
    val user: User? = null,
    val tags: List<Tag>? = null
) : Serializable

data class WelcomeLinks(
    val self: String? = null,
    val html: String? = null,
    val download: String? = null,
    val downloadLocation: String? = null
)

data class Sponsorship(
    val impressionUrls: List<String>? = null,
    val tagline: String? = null,
    val taglineURL: String? = null,
    val sponsor: User? = null
)

data class User(
    val id: String? = null,
    val updatedAt: String? = null,
    val username: String? = null,
    val name: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val twitterUsername: String? = null,
    val portfolioURL: String? = null,
    val bio: String? = null,
    val location: String? = null,
    val links: UserLinks? = null,
    @SerializedName("profile_image")
    val profileImage: ProfileImage? = null,
    val instagramUsername: String? = null,
    val totalCollections: Long? = null,
    val totalLikes: Long? = null,
    val totalPhotos: Long? = null,
    val acceptedTos: Boolean? = null,
    val forHire: Boolean? = null,
    val social: Social? = null
)

data class UserLinks(
    val self: String? = null,
    val html: String? = null,
    val photos: String? = null,
    val likes: String? = null,
    val portfolio: String? = null,
    val following: String? = null,
    val followers: String? = null
)

data class ProfileImage(
    val small: String? = null,
    val medium: String? = null,
    val large: String? = null
)

data class Social(
    val instagramUsername: String? = null,
    val portfolioURL: String? = null,
    val twitterUsername: String? = null,
    val paypalEmail: Any? = null
)

class TopicSubmissions()

data class Urls(
    val raw: String? = null,
    val full: String? = null,
    val regular: String? = null,
    val small: String? = null,
    val thumb: String? = null,
    val smallS3: String? = null
)

