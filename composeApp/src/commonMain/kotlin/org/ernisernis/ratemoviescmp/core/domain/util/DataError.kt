package org.ernisernis.ratemoviescmp.core.domain.util

sealed interface DataError: Error {
    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN,
    }
    // TODO: We can always use this local when working with Room
    enum class Local: DataError {
        DISK_FULL,
        UNKNOWN
    }
}