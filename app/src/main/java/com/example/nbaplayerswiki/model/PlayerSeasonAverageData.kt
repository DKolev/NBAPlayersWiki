package com.example.nbaplayerswiki.model

/**
 * [PlayerSeasonAverageData] is a data class containing the list of all player's
 * season averages details for a certain season
 */
data class PlayerSeasonAverageData(
    val data: Array<PlayerSeasonAverage>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlayerSeasonAverageData

        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }
}