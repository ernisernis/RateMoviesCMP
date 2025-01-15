package org.ernisernis.ratemoviescmp.movie.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkMovieDao {
    @Upsert
    suspend fun upsertMovieEntity(movie: MovieEntity)

    @Query("SELECT * FROM MovieEntity")
    fun getBookmarkMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getBookmarkMovie(id: Int): MovieEntity?

    @Query("DELETE FROM MovieEntity WHERE id = :id")
    suspend fun deleteBookmarkMovie(id: Int)
}