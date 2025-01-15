package org.ernisernis.ratemoviescmp.movie.domain

import kotlinx.coroutines.flow.Flow
import org.ernisernis.ratemoviescmp.core.domain.util.DataError
import org.ernisernis.ratemoviescmp.core.domain.util.EmptyResult
import org.ernisernis.ratemoviescmp.core.domain.util.Result

interface MovieRepository {
    suspend fun getNowPlayingMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getMovieDetail(id: Int): Result<MovieDetail, DataError.Remote>

    fun getBookmarkMovies(): Flow<List<Movie>>
    fun isBookBookmarked(id: Int): Flow<Boolean>
    suspend fun markAsBookmarked(movie: Movie): EmptyResult<DataError.Local>
    suspend fun deleteFromBookmark(id: Int)
}