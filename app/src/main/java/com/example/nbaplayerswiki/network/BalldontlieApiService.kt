package com.example.nbaplayerswiki.network

import com.example.nbaplayerswiki.model.AllPlayersData
import com.example.nbaplayerswiki.model.Player
import com.example.nbaplayerswiki.model.PlayerSeasonAverageData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://www.balldontlie.io/api/v1/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getPlayers] & [getSeasonAverages] methods
 */
interface BalldontlieApiService {
    /**
     * Returns a [List] of [Player]
     */
    @GET("players")
    suspend fun getPlayers() : AllPlayersData

    /**
     * Returns an [Array] of [PlayerSeasonAverageData]
     */
    @GET("season_averages")
    suspend fun getSeasonAverages(
        @Query("season") season: Int, // query param for the API call
        @Query("player_ids[]") id: Int // query param for the API call
    ) : PlayerSeasonAverageData
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object BalldontlieApi {
    val retrofitService: BalldontlieApiService by lazy { retrofit.create(BalldontlieApiService::class.java) }
}