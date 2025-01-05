package org.ernisernis.ratemoviescmp.core.data.networking

import org.ernisernis.ratemoviescmp.BuildKonfig.BASE_URL

fun constructUrl(url: String): String {
    return when {
        url.contains(BASE_URL) -> url
        url.startsWith("/") -> BASE_URL + url.drop(1)
        else -> BASE_URL + url
    }
}