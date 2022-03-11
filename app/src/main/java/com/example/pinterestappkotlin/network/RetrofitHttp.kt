package com.example.pinterestappkotlin.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {

    val IS_TESTER = true

    private val SERVER_DEVELOPMENT = "https://api.unsplash.com/"
    private val SERVER_PRODUCTION = "https://api.unsplash.com/"

    val retrofit =
        Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun server(): String {
        if (IS_TESTER) {
            return SERVER_DEVELOPMENT
        }
        return SERVER_PRODUCTION
    }

    val pinterestService: PinterestService = retrofit.create(PinterestService::class.java)

}