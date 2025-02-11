package org.ernisernis.ratemoviescmp.movie.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val runtimeFormatted: String?,
    val voteAverage: String,
    val creationTime: Long,
)