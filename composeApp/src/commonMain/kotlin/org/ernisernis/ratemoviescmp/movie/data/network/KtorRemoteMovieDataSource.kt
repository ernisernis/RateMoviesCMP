package org.ernisernis.ratemoviescmp.movie.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.ernisernis.ratemoviescmp.BuildKonfig.API_KEY
import org.ernisernis.ratemoviescmp.core.data.networking.constructUrl
import org.ernisernis.ratemoviescmp.core.data.networking.safeCall
import org.ernisernis.ratemoviescmp.core.domain.util.DataError
import org.ernisernis.ratemoviescmp.core.domain.util.Result
import org.ernisernis.ratemoviescmp.core.domain.util.map
import org.ernisernis.ratemoviescmp.movie.data.dto.MovieDetailDto
import org.ernisernis.ratemoviescmp.movie.data.dto.MovieResponseDto

class KtorRemoteMovieDataSource(
    private val httpClient: HttpClient
): RemoteMovieDataSource {
    override suspend fun getNowPlayingMovies(): Result<MovieResponseDto, DataError.Remote> {
        return safeCall<MovieResponseDto> {
            httpClient.get(
                urlString = constructUrl("/movie/now_playing"),
            ) {
                parameter("api_key", API_KEY)
                parameter("include_adult", true)
            }
        }.map { response ->
            response
        }
    }

    override suspend fun getMovieDetail(id: Int): Result<MovieDetailDto, DataError.Remote> {
        return safeCall<MovieDetailDto> {
            httpClient.get(
                urlString = constructUrl("/movie/$id")
            ) {
                parameter("api_key", API_KEY)
                parameter("append_to_response", "credits")
            }
        }.map { response ->
            response
        }
    }

}