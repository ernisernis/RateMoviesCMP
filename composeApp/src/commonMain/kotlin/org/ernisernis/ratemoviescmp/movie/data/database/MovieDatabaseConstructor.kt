package org.ernisernis.ratemoviescmp.movie.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MovieDatabaseConstructor: RoomDatabaseConstructor<BookmarkMovieDatabase> {
    override fun initialize(): BookmarkMovieDatabase
}