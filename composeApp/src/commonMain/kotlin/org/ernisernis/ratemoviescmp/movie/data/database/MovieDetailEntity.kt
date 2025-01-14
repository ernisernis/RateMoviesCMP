package org.ernisernis.ratemoviescmp.movie.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.ernisernis.ratemoviescmp.movie.data.dto.CastDto
import org.ernisernis.ratemoviescmp.movie.data.dto.CrewDto
import org.ernisernis.ratemoviescmp.movie.data.dto.MovieGenreDto

@Entity
data class MovieDetailEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val releaseDate: String,
    val runtime: Int,
    val voteAverage: Double,
    val voteCount: Int,
    val genres: List<MovieGenreDto>,
    val overview: String,
    val cast: List<CastDto>,
    val crew: List<CrewDto>
)
