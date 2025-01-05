package org.ernisernis.ratemoviescmp.core.data.networking

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import org.ernisernis.ratemoviescmp.core.domain.util.DataError
import org.ernisernis.ratemoviescmp.core.domain.util.Result
import kotlin.coroutines.coroutineContext


suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Result<T, DataError.Remote> {
    val response =
        try {
            execute()
        } catch (e: UnresolvedAddressException) {
            return Result.Error(DataError.Remote.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(DataError.Remote.SERIALIZATION)
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            return Result.Error(DataError.Remote.UNKNOWN)
        }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, DataError.Remote> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Result.Error(DataError.Remote.SERVER_ERROR)
            }
        }
        408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT)
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Remote.SERVER_ERROR)
        else -> Result.Error(DataError.Remote.UNKNOWN)
    }
}