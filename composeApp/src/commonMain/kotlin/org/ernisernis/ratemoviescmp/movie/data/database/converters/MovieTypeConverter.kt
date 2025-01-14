package org.ernisernis.ratemoviescmp.movie.data.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import org.ernisernis.ratemoviescmp.movie.data.dto.CastDto
import org.ernisernis.ratemoviescmp.movie.data.dto.CrewDto
import org.ernisernis.ratemoviescmp.movie.data.dto.MovieGenreDto

object MovieTypeConverter {
    @TypeConverter
    fun movieGenreDtoFromString(value: String): List<MovieGenreDto> {
        return Json.decodeFromString<List<MovieGenreDto>>(value)
    }

    @TypeConverter
    fun movieGenreDtoFromList(value: List<MovieGenreDto>): String {
        return Json.encodeToString(value)
    }


    @TypeConverter
    fun movieCastDtoFromString(value: String): List<CastDto> {
        return Json.decodeFromString<List<CastDto>>(value)
    }

    @TypeConverter
    fun movieCastDtoFromList(value: List<CastDto>): String {
        return Json.encodeToString(value)
    }


    @TypeConverter
    fun movieCrewFromString(value: String): List<CrewDto> {
        return Json.decodeFromString<List<CrewDto>>(value)
    }

    @TypeConverter
    fun movieCrewFromList(value: List<CrewDto>): String {
        return Json.encodeToString(value)
    }
}