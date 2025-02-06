package org.ernisernis.ratemoviescmp.movie.data.mappers

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
   )
}

fun Rating.toRatingUi(): RatingUi {
    return RatingUi(
        id = id,
        imageUrl = "https://image.tmdb.org/t/p/w780$posterPath",
        title = title,
        releaseYear = releaseDate.substringBefore("-"),
        runtimeFormatted = runtimeFormatted,
        voteAverage = voteAverage,
        description = description,
        userRating = userRating,
    )
}