package org.ernisernis.ratemoviescmp.movie.data.mappers

import org.ernisernis.ratemoviescmp.core.presentation.getReleaseYear
import org.ernisernis.ratemoviescmp.movie.data.database.entities.RatingEntity
import org.ernisernis.ratemoviescmp.movie.domain.Rating
import org.ernisernis.ratemoviescmp.movie.presentation.models.RatingUi


fun RatingEntity.toRating(): Rating {
   return Rating(
       id = id,
       posterPath = posterPath,
       title = title,
       releaseDate = releaseDate,
       runtimeFormatted = runtimeFormatted,
       voteAverage = voteAverage,
       description = description,
       userRating = userRating,
       creationTime = creationTime,
   )
}

fun Rating.toRatingUi(): RatingUi {
    return RatingUi(
        id = id,
        imageUrl = "https://image.tmdb.org/t/p/w780$posterPath",
        title = title,
        releaseYear = releaseDate.getReleaseYear(),
        runtimeFormatted = runtimeFormatted,
        voteAverage = voteAverage,
        description = description,
        userRating = userRating,
    )
}
fun Rating.toRatingEntity(): RatingEntity {
    return RatingEntity(
        id = id,
        posterPath = posterPath,
        title = title,
        releaseDate = releaseDate,
        runtimeFormatted = runtimeFormatted,
        voteAverage = voteAverage,
        description = description,
        userRating = userRating,
        creationTime = creationTime,
    )
}