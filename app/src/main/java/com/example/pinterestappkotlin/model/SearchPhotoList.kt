package com.example.pinterestappkotlin.model

data class SearchPhotoList (
    val total: Long,
    val totalPages: Long,
    val results: List<PhotoItem>
)

data class Result (
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val promotedAt: String? = null,
    val width: Long,
    val height: Long,
    val color: String,
    val blurHash: String,
    val description: String? = null,
    val altDescription: String,
    val urls: Urls,
    val links: ResultLinks,
    val categories: List<Any?>,
    val likes: Long,
    val likedByUser: Boolean,
    val currentUserCollections: List<Any?>,
    val sponsorship: Any? = null,
    val topicSubmissions: TopicSubmissions,
    val user: User,
    val tags: List<Tag>
)

data class ResultLinks (
    val self: String,
    val html: String,
    val download: String,
    val downloadLocation: String
)

data class Tag (
    val type: Type,
    val title: String,
    val source: Source? = null
)

data class Source (
    val ancestry: Ancestry,
    val title: Title,
    val subtitle: Subtitle,
    val description: String,
    val metaTitle: String,
    val metaDescription: String,
    val coverPhoto: CoverPhoto
)

data class Ancestry (
    val type: Category,
    val category: Category,
    val subcategory: Category
)

data class Category (
    val slug: Slug,
    val prettySlug: PrettySlug
)

enum class PrettySlug {
    Car,
    Images,
    Things
}

enum class Slug {
    Car,
    Images,
    Things
}

data class CoverPhoto (
    val id: ID,
    val createdAt: String,
    val updatedAt: String,
    val promotedAt: String,
    val width: Long,
    val height: Long,
    val color: Color,
    val blurHash: BlurHash,
    val description: String,
    val altDescription: AltDescription,
    val urls: Urls,
    val links: ResultLinks,
    val categories: List<Any?>,
    val likes: Long,
    val likedByUser: Boolean,
    val currentUserCollections: List<Any?>,
    val sponsorship: Any? = null,
    val topicSubmissions: TopicSubmissions,
    val user: User
)

enum class AltDescription {
    WhiteCar
}

enum class BlurHash {
    L95Y4I9MwNWAjJA8HxvIA
}

enum class Color {
    The262640
}

enum class ID {
    M3MLnR90UM
}

enum class Subtitle {
    DownloadFreeCarImages
}

enum class Title {
    CarImagesPictures
}

enum class Type {
    LandingPage,
    Search
}
