package org.ernisernis.ratemoviescmp.movie.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieResponseDto(
   @SerialName("results") val results: List<MovieDto>,
)
