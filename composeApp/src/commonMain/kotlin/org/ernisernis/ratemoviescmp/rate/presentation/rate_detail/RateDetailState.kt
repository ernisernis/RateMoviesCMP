package org.ernisernis.ratemoviescmp.rate.presentation.rate_detail

import org.ernisernis.ratemoviescmp.rate.domain.RateType

data class RateDetailState(
    val id: Int = 0,
    val type: RateType = RateType.MOVIE,
    val bannerUrl: String = "",
    val imageUrl: String = "",
    val title: String = "",
    val selectedIndex: Int = 0,
)


