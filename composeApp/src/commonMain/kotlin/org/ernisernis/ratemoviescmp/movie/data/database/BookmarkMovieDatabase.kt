package org.ernisernis.ratemoviescmp.movie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.ernisernis.ratemoviescmp.movie.data.database.converters.IntListTypeConverter
import org.ernisernis.ratemoviescmp.movie.data.database.converters.MovieTypeConverter


@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 2
)
@TypeConverters(
    IntListTypeConverter::class,
    MovieTypeConverter::class,
)
abstract class BookmarkMovieDatabase: RoomDatabase() {
    abstract val bookmarkMovieDao: BookmarkMovieDao

    companion object {
        const val DB_NAME = "movie.db"
    }
}