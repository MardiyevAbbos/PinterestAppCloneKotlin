package com.example.pinterestappkotlin.model

data class RelatedPhotos(
    var total: Int? = null,
    var results: ArrayList<PhotoItem>? = null
)
