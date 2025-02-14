package org.ernisernis.ratemoviescmp.movie.data.repository

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.ernisernis.ratemoviescmp.core.domain.util.DataError
import org.ernisernis.ratemoviescmp.core.domain.util.EmptyResult
import org.ernisernis.ratemoviescmp.core.domain.util.Result
import org.ernisernis.ratemoviescmp.movie.data.database.RatingDao
import org.ernisernis.ratemoviescmp.movie.data.mappers.toRating
import org.ernisernis.ratemoviescmp.movie.domain.ProfileRepository
import org.ernisernis.ratemoviescmp.movie.domain.Rating

class DefaultProfileRepository(
    private val ratingDao: RatingDao,
): ProfileRepository {

    override fun getRatingsOrderedByCreatedTime(): Flow<List<Rating>> {
        return ratingDao
            .getRatingsOrderedByCreatedTime()
            .map { ratingEntities ->
                ratingEntities.map { it.toRating() }
            }
    }

    override suspend fun deleteRating(id: Int): EmptyResult<DataError.Local> {
        return try {
            ratingDao.deleteRating(id)
            ratingDao.deleteMovieEntity(id)
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }
}

