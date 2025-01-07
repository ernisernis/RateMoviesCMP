package org.ernisernis.ratemoviescmp.rate.presentation.rate_detail

sealed interface RateDetailAction {
    data class OnRateClick(val index: Int): RateDetailAction
    object OnRateSubmit: RateDetailAction
}