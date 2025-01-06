package org.ernisernis.ratemoviescmp.movie.presentation.models

import org.ernisernis.ratemoviescmp.movie.domain.Crew

data class CrewUi(
    val id: Int,
    val name: String,
    val job: String,
    val profilePath: String,
)

fun Crew.toCrewUi(): CrewUi {
    return CrewUi(
        id = id,
        name = name,
        job = job,
        profilePath = profilePath,
    )
}
