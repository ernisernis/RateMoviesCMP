package org.ernisernis.ratemoviescmp.movie.domain

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getRatingsOrderedByCreatedTime(): Flow<List<Rating>>
}