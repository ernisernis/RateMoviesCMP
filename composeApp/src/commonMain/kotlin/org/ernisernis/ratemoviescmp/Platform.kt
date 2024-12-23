package org.ernisernis.ratemoviescmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform