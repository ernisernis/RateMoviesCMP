package org.ernisernis.ratemoviescmp.movie.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<BookmarkMovieDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "RateMovies")
            os.contains("mac") -> File(userHome, "Library/Application Support/RateMovies")
            else -> File(userHome, ".local/share/RateMovies")
        }

        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, BookmarkMovieDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}