package org.ernisernis.ratemoviescmp.movie.data.repository

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.ernisernis.ratemoviescmp.core.domain.util.DataError
import org.ernisernis.ratemoviescmp.core.domain.util.EmptyResult
import org.ernisernis.ratemoviescmp.core.domain.util.Result
import org.ernisernis.ratemoviescmp.core.domain.util.map
import org.ernisernis.ratemoviescmp.movie.data.database.BookmarkMovieDao
import org.ernisernis.ratemoviescmp.movie.data.mappers.toBookmarkEntity
import org.ernisernis.ratemoviescmp.movie.data.mappers.toBookmarkMovie
import org.ernisernis.ratemoviescmp.movie.data.mappers.toMovie
import org.ernisernis.ratemoviescmp.movie.data.mappers.toMovieDetail
import org.ernisernis.ratemoviescmp.movie.data.mappers.toMovieEntity
import org.ernisernis.ratemoviescmp.movie.data.network.RemoteMovieDataSource
import org.ernisernis.ratemoviescmp.movie.domain.BookmarkMovie
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
        bookmarkMovieDao.deleteBookmarkMovie(id)
    }
}