package org.ernisernis.ratemoviescmp.movie.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<MovieDatabase>
}