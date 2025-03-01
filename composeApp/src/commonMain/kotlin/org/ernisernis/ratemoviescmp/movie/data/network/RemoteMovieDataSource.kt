package org.ernisernis.ratemoviescmp.movie.data.network

import org.ernisernis.ratemoviescmp.core.domain.util.DataError
import org.ernisernis.ratemoviescmp.core.domain.util.Result
import org.ernisernis.ratemoviescmp.movie.data.dto.MovieDetailDto
import org.ernisernis.ratemoviescmp.movie.data.dto.MovieResponseDto

interface RemoteMovieDataSource {
    suspend fun getNowPlayingMovies(): Result<MovieResponseDto, DataError.Remote>
    suspend fun getMovieDetail(id: Int): Result<MovieDetailDto, DataError.Remote>
}