package org.ernisernis.ratemoviescmp.movie.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkMovieDao {

    @Upsert
    suspend fun upsertMovieEntity(movie: MovieEntity)

    @Upsert
    suspend fun upsertMovieDetailEntity(movieDetailEntity: MovieDetailEntity)

    @Transaction
    suspend fun upsertBookmarkMovie(movie: MovieEntity, movieDetailEntity: MovieDetailEntity) {
        upsertMovieEntity(movie)
        upsertMovieDetailEntity(movieDetailEntity)
    }

    @Query("SELECT * FROM MovieEntity")
    fun getBookmarkMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getBookmarkMovie(id: Int): MovieEntity?

    @Query("SELECT * FROM MovieDetailEntity WHERE id = :id")
    suspend fun getBookmarkMovieDetail(id: Int): MovieDetailEntity?

    @Query("DELETE FROM MovieEntity WHERE id = :id")
    suspend fun deleteBookmarkMovie(id: Int)

    @Query("DELETE FROM MovieDetailEntity WHERE id = :id")
    suspend fun deleteBookmarkMovieDetail(id: Int)

    @Transaction
    suspend fun deleteMovieAndDetail(id: Int) {
        deleteBookmarkMovie(id)
        deleteBookmarkMovieDetail(id)
    }
}