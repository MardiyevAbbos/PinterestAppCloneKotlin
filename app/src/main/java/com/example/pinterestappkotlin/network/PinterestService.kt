package com.example.pinterestappkotlin.network

import com.example.pinterestappkotlin.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PinterestService {

    companion object {
        const val access_key: String = "8QHnqWs2tJaeeMUvjMbBDcRgZhyzNvSNSXuMVcXqJ3U"
    }

    @Headers("Authorization: Client-ID $access_key")
    @GET("photos")
    fun listPhotos(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = 20
    ): Call<ArrayList<PhotoItem>>


    @Headers("Authorization: Client-ID $access_key")
    @GET("photos/{id}/related")
    fun getRelatedPhotos(@Path("id") id: String): Call<RelatedPhotos>  // get similar photos

    @Headers("Authorization: Client-ID $access_key")
    @GET("search/photos")
    fun getSearchPhotoList(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = 10
    ): Call<SearchPhotoList>

    @Headers("Authorization: Client-ID $access_key")
    @GET("collections")
    fun listCollections(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = 10
    ): Call<ArrayList<WelcomeElement>>

    @Headers("Authorization: Client-ID $access_key")
    @GET("users/mardiyev_abbos")
    fun getMyProfile(): Call<MyProfile>

}