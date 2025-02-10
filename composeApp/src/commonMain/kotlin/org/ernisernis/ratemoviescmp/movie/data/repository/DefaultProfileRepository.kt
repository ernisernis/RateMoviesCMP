package org.ernisernis.ratemoviescmp.movie.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.ernisernis.ratemoviescmp.movie.data.database.RatingDao
import org.ernisernis.ratemoviescmp.movie.data.mappers.toRating
import org.ernisernis.ratemoviescmp.movie.domain.ProfileRepository
import org.ernisernis.ratemoviescmp.movie.domain.Rating

class DefaultProfileRepository(
    private val ratingDao: RatingDao,
): ProfileRepository {

    override fun getRatedMovies(): Flow<List<Rating>> {
        return ratingDao
            .getRatings()
            .map { ratingEntities ->
                ratingEntities.map { it.toRating() }
            }
    }
}

