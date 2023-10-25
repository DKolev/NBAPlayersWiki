package com.example.nbaplayerswiki.model

import com.squareup.moshi.Json

/**
 * [Team] is a data class containing all the player's team details
 */
data class Team(
    @Json(name = "id") val teamId: Int,
    @Json(name = "abbreviation") val teamAbbreviation: String,
    @Json(name = "city") val teamCity: String,
    @Json(name = "conference") val teamConference: String,
    @Json(name = "division") val teamDivision: String,
    @Json(name = "full_name") val teamFullName: String,
    @Json(name = "name") val teamName: String,
)