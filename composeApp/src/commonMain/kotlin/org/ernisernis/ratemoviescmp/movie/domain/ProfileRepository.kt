package org.ernisernis.ratemoviescmp.movie.domain

import kotlinx.coroutines.flow.Flow
import org.ernisernis.ratemoviescmp.core.domain.util.DataError
import org.ernisernis.ratemoviescmp.core.domain.util.EmptyResult

interface ProfileRepository {
    fun getRatingsOrderedByCreatedTime(): Flow<List<Rating>>
    suspend fun deleteRating(id: Int): EmptyResult<DataError.Local>
}