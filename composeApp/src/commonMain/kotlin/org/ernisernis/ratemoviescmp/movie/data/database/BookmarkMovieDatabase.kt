package org.ernisernis.ratemoviescmp.movie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.ernisernis.ratemoviescmp.movie.data.database.converters.IntListTypeConverter
import org.ernisernis.ratemoviescmp.movie.data.database.converters.MovieTypeConverter
import org.ernisernis.ratemoviescmp.movie.data.database.entities.BookmarkEntity
import org.ernisernis.ratemoviescmp.movie.data.database.entities.MovieEntity


@Database(
    entities = [
        MovieEntity::class,
        BookmarkEntity::class,
    ],
    version = 3
)
@TypeConverters(
    IntListTypeConverter::class,
    MovieTypeConverter::class,
)
abstract class MovieDatabase: RoomDatabase() {
    abstract val bookmarkMovieDao: BookmarkMovieDao

    companion object {
        const val DB_NAME = "movie.db"
    }
}