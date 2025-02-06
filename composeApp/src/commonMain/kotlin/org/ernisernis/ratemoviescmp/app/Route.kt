package org.ernisernis.ratemoviescmp.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MovieGraph: Route

    @Serializable
    data object MovieList: Route

    @Serializable
    data class MovieDetail(val id: Int): Route

    @Serializable
    data object MovieRate: Route

    @Serializable
    data object MovieBookmark: Route

    @Serializable
    data object MovieProfile: Route
}