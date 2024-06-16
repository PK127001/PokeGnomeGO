package com.example.pokegnomego.com.example.pokegnomego.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("comments/{gnomeId}")
    fun getComments(@Path("gnomeId") gnomeId: Int): Call<List<Comment>>

    @POST("comments/{gnomeId}")
    fun addComment(
        @Path("gnomeId") gnomeId: Int,
        @Body comment: Comment
    ): Call<Comment>
}