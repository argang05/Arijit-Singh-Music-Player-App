package com.example.musicplayerapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    //Passing Query to fetch data from "MyMusicData" DataClass file.
    //@GET because we are getting or requesting some data with the specified endpoint(in Deezer).
    @Headers(
        "X-RapidAPI-Key:8347e67cf6mshffa97e9ebb0c191p1b0e83jsn5f3b31b26cf3",
        "X-RapidAPI-Host:deezerdevs-deezer.p.rapidapi.com",
    )
    @GET("search")
    fun getData(@Query("q") query: String) : Call<MyMusicData?>
    //Here we searching music by the name of the artist:
}
