package com.example.pokegnomego.com.example.pokegnomego.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("comments/{gnomeId}")
    fun getComments(@Path("gnomeId") gnomeId: Int): Call<List<Comment>>
}