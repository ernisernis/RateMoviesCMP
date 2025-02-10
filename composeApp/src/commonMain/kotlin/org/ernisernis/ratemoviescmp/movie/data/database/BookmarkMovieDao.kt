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

    // Do not delete MovieEntity if Review(Rating) exists
    @Query("""
    DELETE FROM MovieEntity 
    WHERE id = :id 
    AND NOT EXISTS (
        SELECT 1 FROM RatingEntity WHERE id = :id
    )
    """)
    suspend fun deleteMovieEntity(id: Int)

    @Query("DELETE FROM BookmarkEntity WHERE id = :id")
    suspend fun deleteBookmarkEntity(id: Int)
}