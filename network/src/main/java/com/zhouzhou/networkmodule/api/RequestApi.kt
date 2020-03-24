package com.zhouzhou.networkmodule.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestApi {
    fun test()

    @GET("/get")
    fun getNews(
        @Query("channel") channel: String,
        @Query("num") num: Int,
        @Query("start") start: Int
    ): Observable<ResponseBody>

    @GET("/channel")
    fun getChannels(): Observable<ResponseBody>

}