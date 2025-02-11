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

    @Query("SELECT * FROM RatingEntity ORDER BY creationTime DESC")
    fun getRatingsOrderedByCreatedTime(): Flow<List<RatingEntity>>

    @Query("SELECT * FROM RatingEntity WHERE id = :id")
    suspend fun getRatingEntity(id: Int): RatingEntity?

    // TODO Fix deleteRating, need to add a separate delete RatingEntity
    @Query("DELETE FROM MovieEntity WHERE id = :id")
    suspend fun deleteRating(id: Int)
}