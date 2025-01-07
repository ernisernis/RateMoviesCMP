package org.ernisernis.ratemoviescmp.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MovieGraph: Route

    @Serializable
    data object MovieList: Route

    @Serializable
    data class MovieDetail(
        val id: Int,
        val bannerUrl: String,
        val title: String,
        val imageUrl: String,
    ): Route

    @Serializable
    data class RateDetail(
        val id: Int,
        val bannerUrl: String,
        val title: String,
        val imageUrl: String,
    ): Route
}