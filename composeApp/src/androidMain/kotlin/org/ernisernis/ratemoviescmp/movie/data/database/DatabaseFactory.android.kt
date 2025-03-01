package org.ernisernis.ratemoviescmp.movie.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<MovieDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(MovieDatabase.DB_NAME)

        return Room.databaseBuilder(
            context,
            dbFile.absolutePath
        )
    }
}