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
    val publishedAt: String? = null,
    val lastCollectedAt: String? = null,
    val updatedAt: String? = null,
    val curated: Boolean? = null,
    val featured: Boolean? = null,
    val totalPhotos: Long? = null,
    val private: Boolean? = null,
    val shareKey: String? = null,
    val tags: List<Tag>? = null,
    val links: WelcomeLinks? = null,
    val user: User? = null,
    @SerializedName("cover_photo")
    val coverPhoto: WelcomeCoverPhoto? = null,
    val previewPhotos: List<PreviewPhoto>? = null
)

data class WelcomeCoverPhoto (
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
    val links: CoverPhotoLinks? = null,
    val categories: List<Any?>? = null,
    val likes: Long? = null,
    val likedByUser: Boolean? = null,
    val currentUserCollections: List<Any?>? = null,
    val sponsorship: Any? = null,
    val topicSubmissions: PurpleTopicSubmissions? = null,
    val user: User? = null
)

data class CoverPhotoLinks (
    val self: String? = null,
    val html: String? = null,
    val download: String? = null,
    val downloadLocation: String? = null
)

data class PurpleTopicSubmissions (
    val foodDrink: The3_DRenders? = null,
    val fashion: The3_DRenders? = null,
    val businessWork: The3_DRenders? = null,
    val the3DRenders: The3_DRenders? = null
)

data class The3_DRenders (
    val status: Status? = null,
    val approvedOn: String? = null
)

enum class Status {
    Approved
}

data class PreviewPhoto (
    val id: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val blurHash: String? = null,
    val urls: Urls? = null
)


data class SourceCoverPhoto (
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
    val links: CoverPhotoLinks? = null,
    val categories: List<Any?>? = null,
    val likes: Long? = null,
    val likedByUser: Boolean? = null,
    val currentUserCollections: List<Any?>? = null,
    val sponsorship: Any? = null,
    val topicSubmissions: FluffyTopicSubmissions? = null,
    val user: User? = null
)

data class FluffyTopicSubmissions (
    val foodDrink: The3_DRenders? = null,
    val health: The3_DRenders? = null,
    val texturesPatterns: The3_DRenders? = null,
    val wallpapers: The3_DRenders? = null,
    val architecture: The3_DRenders? = null,
    val nature: The3_DRenders? = null,
    val spirituality: The3_DRenders? = null
)


