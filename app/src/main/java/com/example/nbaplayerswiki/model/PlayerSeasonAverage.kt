package com.example.nbaplayerswiki.model

import com.squareup.moshi.Json

/**
 * [PlayerSeasonAverage] is a data class containing all the season averages
 * for a player for a certain season
 */
data class PlayerSeasonAverage(
    @Json(name = "games_played") val gamesPlayed: Int?,
    @Json(name = "player_id") val playerId: Int?,
    val season: Int?,
    val min: String?,
    val fqm: Double?,
    val fqa: Double?,
    val fg3m: Double?,
    val ftm: Double?,
    val fta: Double?,
    val oreb: Double?,
    val dreb: Double?,
    val reb: Double?,
    val ast: Double?,
    val stl: Double?,
    val blk: Double?,
    val turnover: Double?,
    val pf: Double?,
    val pts: Double?,
    val fg_pts: Double?,
    val fg3_pts: Double?,
    val ft_ptc: Double?,
)
