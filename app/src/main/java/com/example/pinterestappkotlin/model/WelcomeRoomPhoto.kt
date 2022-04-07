package com.example.pinterestappkotlin.model

import com.google.gson.annotations.SerializedName

data class WelcomeRoomPhoto (
    val id: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val promotedAt: String? = null,
    val width: Long? = null,
    val height: Long? = null,
    val color: String? = null,
    val blurHash: String? = null,
    @SerializedName("description")
    val description: Any? = null,
    val altDescription: Any? = null,
    val urls: Urls? = null,
    val links: WelcomeLinks? = null,
    val categories: List<Any?>? = null,
    @SerializedName("likes")
    val likes: Long? = null,
    val likedByUser: Boolean? = null,
    val currentUserCollections: List<Any?>? = null,
    @SerializedName("sponsorship")
    val sponsorship: Any? = null,
    val topicSubmissions: WelcomeTopicSubmissions? = null,
    @SerializedName("user")
    val user: User? = null,
    val tags: List<Any?>? = null,
    val exif: Exif? = null,
    val location: Location? = null,
    val meta: Meta? = null,
    val publicDomain: Boolean? = null,
    val tagsPreview: List<Any?>? = null,
    val relatedCollections: RelatedCollections? = null,
    val views: Long? = null,
    val downloads: Long? = null,
    val topics: List<Any?>? = null
)

data class Exif (
    val make: String? = null,
    val model: String? = null,
    val name: String? = null,
    val exposureTime: String? = null,
    val aperture: String? = null,
    val focalLength: String? = null,
    val iso: Long? = null
)

data class Location (
    val title: String? = null,
    val name: String? = null,
    val city: Any? = null,
    val country: Any? = null,
    val position: Position? = null
)

data class Position (
    val latitude: Any? = null,
    val longitude: Any? = null
)

data class RelatedCollections (
    val total: Long? = null,
    val type: String? = null,
    val results: List<ResultRoom>? = null
)

data class ResultRoom (
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val lastCollectedAt: String? = null,
    val updatedAt: String? = null,
    val curated: Boolean? = null,
    val featured: Boolean? = null,
    val totalPhotos: Long? = null,
    val private: Boolean? = null,
    val shareKey: String? = null,
    val tags: List<Tag>? = null,
    val links: ResultLinks? = null,
    val user: User? = null,
    val coverPhoto: ResultCoverPhoto? = null,
    val previewPhotos: List<PreviewPhoto>? = null
)

data class ResultCoverPhoto (
    val id: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val promotedAt: String? = null,
    val width: Long? = null,
    val height: Long? = null,
    val color: String? = null,
    val blurHash: String? = null,
    val description: String? = null,
    val altDescription: String? = null,
    val urls: Urls? = null,
    val links: WelcomeLinks? = null,
    val categories: List<Any?>? = null,
    val likes: Long? = null,
    val likedByUser: Boolean? = null,
    val currentUserCollections: List<Any?>? = null,
    val sponsorship: Any? = null,
    val topicSubmissions: PurpleTopicSubmissions? = null,
    val user: User? = null
)

data class Wallpapers (
    val status: String? = null,
    val approvedOn: String? = null
)

class WelcomeTopicSubmissions()

