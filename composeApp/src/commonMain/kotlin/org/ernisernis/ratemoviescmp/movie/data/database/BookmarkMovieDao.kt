package org.ernisernis.ratemoviescmp.movie.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.ernisernis.ratemoviescmp.movie.data.database.entities.BookmarkEntity
import org.ernisernis.ratemoviescmp.movie.data.database.entities.MovieEntity

@Dao
interface BookmarkMovieDao {
    @Upsert
    suspend fun upsertMovieEntity(movie: MovieEntity)

    @Upsert
    suspend fun upsertBookmarkEntity(bookmark: BookmarkEntity)

    @Query("SELECT * FROM BookmarkEntity")
    fun getBookmarkMovies(): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getMovieEntity(id: Int): MovieEntity?

    // TODO: Delete BookmarkEntity and delete MovieEntity IF it does not exist on RatingEntity
    @Query("DELETE FROM MovieEntity WHERE id = :id")
    suspend fun deleteBookmarkMovie(id: Int)
}