package org.ernisernis.ratemoviescmp.movie.data.repository

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.ernisernis.ratemoviescmp.core.domain.util.DataError
import org.ernisernis.ratemoviescmp.core.domain.util.EmptyResult
import org.ernisernis.ratemoviescmp.core.domain.util.Result
import org.ernisernis.ratemoviescmp.core.domain.util.map
import org.ernisernis.ratemoviescmp.movie.data.database.BookmarkMovieDao
import org.ernisernis.ratemoviescmp.movie.data.mappers.toBookEntity
import org.ernisernis.ratemoviescmp.movie.data.mappers.toMovie
import org.ernisernis.ratemoviescmp.movie.data.mappers.toMovieDetail
import org.ernisernis.ratemoviescmp.movie.data.mappers.toMovieDetailEntity
import org.ernisernis.ratemoviescmp.movie.data.mappers.toMovieDetails
import org.ernisernis.ratemoviescmp.movie.data.network.RemoteMovieDataSource
import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.domain.MovieDetail
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository

class DefaultMovieRepository(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val bookmarkMovieDao: BookmarkMovieDao,
): MovieRepository {
    override suspend fun getNowPlayingMovies(): Result<List<Movie>, DataError.Remote> {
        return remoteMovieDataSource
            .getNowPlayingMovies()
            .map { dto ->
                dto.results.map { it.toMovie() }
            }
    }
    override suspend fun getMovieDetail(id: Int): Result<MovieDetail, DataError.Remote> {
        val localResult = bookmarkMovieDao.getBookmarkMovieDetail(id)

        return if (localResult == null) {
            remoteMovieDataSource
                .getMovieDetail(id)
                .map { dto ->
                    dto.toMovieDetails()
                }
        } else {
            Result.Success(localResult.toMovieDetail())
        }
    }

    override fun getBookmarkMovies(): Flow<List<Movie>> {
        return bookmarkMovieDao
            .getBookmarkMovies()
            .map { movieEntities ->
               movieEntities.map { it.toMovie() }
            }
    }

    override fun isBookBookmarked(id: Int): Flow<Boolean> {
        return bookmarkMovieDao
            .getBookmarkMovies()
            .map { bookEntities ->
               bookEntities.any { it.id == id }
            }
    }

    override suspend fun markAsBookmarked(movie: Movie, movieDetail: MovieDetail): EmptyResult<DataError.Local> {
        return try {
            bookmarkMovieDao.upsertBookmarkMovie(movie.toBookEntity(), movieDetail.toMovieDetailEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromBookmark(id: Int) {
        bookmarkMovieDao.deleteMovieAndDetail(id)
    }
}