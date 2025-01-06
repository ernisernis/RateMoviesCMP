package org.ernisernis.ratemoviescmp.movie.domain

import org.ernisernis.ratemoviescmp.core.domain.util.DataError
import org.ernisernis.ratemoviescmp.core.domain.util.Result

interface MovieRepository {
    suspend fun getNowPlayingMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getMovieDetail(id: Int): Result<MovieDetail, DataError.Remote>
}