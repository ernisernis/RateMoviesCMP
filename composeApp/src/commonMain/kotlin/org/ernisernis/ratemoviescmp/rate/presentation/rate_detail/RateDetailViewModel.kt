package org.ernisernis.ratemoviescmp.rate.presentation.rate_detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.ernisernis.ratemoviescmp.rate.domain.RateType

class RateDetailViewModel(): ViewModel() {

    private val _state = MutableStateFlow(RateDetailState())
    val state = _state.asStateFlow()

    fun initData(
        id: Int,
        title: String,
        bannerUrl: String,
        imageUrl: String,
        rateType: RateType,
    ) {
        _state.update { it.copy(
            id = id,
            bannerUrl = bannerUrl,
            imageUrl = imageUrl,
            title = title,
            type = rateType,
        ) }
    }

    fun onAction(action: RateDetailAction) {
       when (action) {
           is RateDetailAction.OnRateClick -> {
               _state.update {
                   it.copy(
                       selectedIndex = action.index,
                   )
               }
           }
           RateDetailAction.OnRateSubmit -> {

           }
       }
    }
}