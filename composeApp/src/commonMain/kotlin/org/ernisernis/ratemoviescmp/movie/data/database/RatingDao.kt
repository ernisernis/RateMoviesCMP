package org.ernisernis.ratemoviescmp.movie.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.ernisernis.ratemoviescmp.movie.data.database.entities.MovieEntity
import org.ernisernis.ratemoviescmp.movie.data.database.entities.RatingEntity

@Dao
interface RatingDao {
    @Upsert
    suspend fun upsertMovieEntity(movie: MovieEntity)

    @Upsert
    suspend fun upsertRatingEntity(rating: RatingEntity)

    @Query("SELECT * FROM RatingEntity")
    fun getRatings(): Flow<List<RatingEntity>>

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getMovieEntity(id: Int): MovieEntity?

    // TODO: Delete BookmarkEntity and delete MovieEntity IF it does not exist on RatingEntity
    @Query("DELETE FROM MovieEntity WHERE id = :id")
    suspend fun deleteRating(id: Int)

}