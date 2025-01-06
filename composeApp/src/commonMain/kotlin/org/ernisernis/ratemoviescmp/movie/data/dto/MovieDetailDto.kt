package org.ernisernis.ratemoviescmp.movie.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    @SerialName("id") val id: Int,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("runtime") val runtime: Int,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int,
    @SerialName("genres") val genres: List<MovieGenreDto>,
    @SerialName("overview") val overview: String,
    @SerialName("credits") val credits: CreditsResponseDto,
)

@Serializable
data class MovieGenreDto(
    val id: Int,
    val name: String,
)

@Serializable
data class CreditsResponseDto(
    val cast: List<CastDto>,
    val crew: List<CrewDto>,
)

@Serializable
data class CastDto(
    val id: Int,
    val name: String,
    @SerialName("profile_path") val profilePath: String?,
    val character: String,
)

@Serializable
data class CrewDto(
    val id: Int,
    val name: String,
    val job: String,
    @SerialName("profile_path") val profilePath: String?,
)

