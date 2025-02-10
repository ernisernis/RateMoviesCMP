package org.ernisernis.ratemoviescmp.movie.data.repository

import androidx.sqlite.SQLiteException
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.ernisernis.ratemoviescmp.core.domain.util.DataError
import org.ernisernis.ratemoviescmp.core.domain.util.EmptyResult
import org.ernisernis.ratemoviescmp.core.domain.util.Result
import org.ernisernis.ratemoviescmp.core.domain.util.map
import org.ernisernis.ratemoviescmp.movie.data.database.BookmarkMovieDao
import org.ernisernis.ratemoviescmp.movie.data.database.RatingDao
import org.ernisernis.ratemoviescmp.movie.data.mappers.toBookmarkEntity
import org.ernisernis.ratemoviescmp.movie.data.mappers.toBookmarkMovie
import org.ernisernis.ratemoviescmp.movie.data.mappers.toMovie
import org.ernisernis.ratemoviescmp.movie.data.mappers.toMovieDetail
import org.ernisernis.ratemoviescmp.movie.data.mappers.toMovieEntity
import org.ernisernis.ratemoviescmp.movie.data.mappers.toRating
import org.ernisernis.ratemoviescmp.movie.data.mappers.toRatingEntity
import org.ernisernis.ratemoviescmp.movie.data.network.RemoteMovieDataSource
import org.ernisernis.ratemoviescmp.movie.domain.BookmarkMovie
import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.domain.MovieDetail
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository
import org.ernisernis.ratemoviescmp.movie.domain.Rating

class DefaultMovieRepository(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val bookmarkMovieDao: BookmarkMovieDao,
    private val ratingDao: RatingDao,
): MovieRepository {
    override suspend fun getNowPlayingMovies(): Result<List<Movie>, DataError.Remote> {
        return remoteMovieDataSource
            .getNowPlayingMovies()
            .map { dto ->
                dto.results.map { it.toMovie() }
            }
    }
    override suspend fun getMovieDetail(id: Int): Result<MovieDetail, DataError.Remote> {
        val localResult = bookmarkMovieDao.getMovieEntity(id)

        return if (localResult == null) {
            remoteMovieDataSource
                .getMovieDetail(id)
                .map { dto ->
                    dto.toMovieDetail()
                }
        } else {
            Result.Success(localResult.movieDetailDto?.toMovieDetail()!!)
        }
    }

    override suspend fun getRating(id: Int): Result<Rating, DataError.Local> {
        val result = ratingDao.getRatingEntity(id)

        return if (result == null) {
            Result.Error(DataError.Local.DISK_FULL)
        } else {
            Result.Success(result.toRating())
        }
    }

    override suspend fun getMovie(id: Int): Result<Movie, DataError.Local> {
        val localResult = bookmarkMovieDao.getMovieEntity(id)

        return if (localResult == null) {
            Result.Error(DataError.Local.UNKNOWN)
        } else {
            Result.Success(localResult.toMovie())
        }
    }

    override fun getBookmarkMovies(): Flow<List<BookmarkMovie>> {
        return bookmarkMovieDao
            .getBookmarkMovies()
            .map { bookmarkEntities ->
               bookmarkEntities.map { it.toBookmarkMovie() }
            }
    }

    override fun isBookBookmarked(id: Int): Flow<Boolean> {
        return bookmarkMovieDao
            .getBookmarkMovies()
            .map { bookEntities ->
               bookEntities.any { it.id == id }
            }
    }

    override suspend fun rateMovie(movie: Movie, rating: Rating): EmptyResult<DataError.Local> {
        return try {
            ratingDao.upsertMovieEntity(movie.toMovieEntity())
            ratingDao.upsertRatingEntity(rating.toRatingEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun markAsBookmarked(movie: Movie): EmptyResult<DataError.Local> {
        return try {
            bookmarkMovieDao.upsertMovieEntity(movie.toMovieEntity())
            bookmarkMovieDao.upsertBookmarkEntity(movie.toBookmarkEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromBookmark(id: Int) {
        bookmarkMovieDao.deleteBookmarkEntity(id)
        bookmarkMovieDao.deleteMovieEntity(id)
    }
}