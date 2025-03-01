package org.ernisernis.ratemoviescmp.movie.domain

import kotlinx.coroutines.flow.Flow
import org.ernisernis.ratemoviescmp.core.domain.util.DataError
import org.ernisernis.ratemoviescmp.core.domain.util.EmptyResult
import org.ernisernis.ratemoviescmp.core.domain.util.Result

interface MovieRepository {
    suspend fun getNowPlayingMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getMovieDetail(id: Int): Result<MovieDetail, DataError.Remote>

    suspend fun getRating(id: Int): Result<Rating, DataError.Local>
    suspend fun getMovie(id: Int): Result<Movie, DataError.Local>
    fun getBookmarksOrderedByCreatedTime(): Flow<List<BookmarkMovie>>
    fun isBookBookmarked(id: Int): Flow<Boolean>
    suspend fun rateMovie(movie: Movie, rating: Rating): EmptyResult<DataError.Local>
    suspend fun markAsBookmarked(movie: Movie): EmptyResult<DataError.Local>
    suspend fun deleteFromBookmark(id: Int)
}