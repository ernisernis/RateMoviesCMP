package org.ernisernis.ratemoviescmp.movie.data.repository

import org.ernisernis.ratemoviescmp.core.domain.util.DataError
import org.ernisernis.ratemoviescmp.core.domain.util.Result
import org.ernisernis.ratemoviescmp.core.domain.util.map
import org.ernisernis.ratemoviescmp.movie.data.mappers.toMovie
import org.ernisernis.ratemoviescmp.movie.data.mappers.toMovieDetails
import org.ernisernis.ratemoviescmp.movie.data.network.RemoteMovieDataSource
import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.domain.MovieDetail
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository

class DefaultMovieRepository(
    private val remoteMovieDataSource: RemoteMovieDataSource
): MovieRepository {
    override suspend fun getNowPlayingMovies(): Result<List<Movie>, DataError.Remote> {
        return remoteMovieDataSource
            .getNowPlayingMovies()
            .map { dto ->
                dto.results.map { it.toMovie() }
            }
    }
    override suspend fun getMovieDetail(id: Int): Result<MovieDetail, DataError.Remote> {
        return remoteMovieDataSource
            .getMovieDetail(id)
            .map { dto ->
                dto.toMovieDetails()
            }
    }
}