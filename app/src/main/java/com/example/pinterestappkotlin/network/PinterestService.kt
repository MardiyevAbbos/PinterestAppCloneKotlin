package com.example.pinterestappkotlin.network

import com.example.pinterestappkotlin.model.PhotoItem
import com.example.pinterestappkotlin.model.RelatedPhotos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PinterestService {

    companion object{
        const val access_key: String = "T85zT-exD9KSyExMcV0RDV8NMHJpffvpe8EYWe4fPpw"
    }

    @Headers("Authorization: Client-ID $access_key")
    @GET("photos")
    fun listPhotos(@Query("page") page: Int, @Query("per_page") per_page: Int = 20) : Call<ArrayList<PhotoItem>>

    @Headers("Authorization: Client-ID $access_key")
    @GET("photos/{id}/related")
    fun getRelatedPhotos(@Path("id") id: String): Call<RelatedPhotos>  // get similar photos

}