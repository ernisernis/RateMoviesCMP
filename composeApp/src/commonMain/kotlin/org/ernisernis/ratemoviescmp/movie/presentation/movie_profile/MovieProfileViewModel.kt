package org.ernisernis.ratemoviescmp.movie.presentation.movie_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ernisernis.ratemoviescmp.movie.data.mappers.toRatingUi
import org.ernisernis.ratemoviescmp.movie.domain.ProfileRepository

class MovieProfileViewModel(
    private val profileRepository: ProfileRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MovieProfileState())
    val state = _state
        .onStart {
            observeRatings()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieProfileState()
        )

    private fun observeRatings() {
        profileRepository
            .getRatingsOrderedByCreatedTime()
            .onEach { ratings ->
                val ratingsUi = ratings.map {
                    it.toRatingUi()
                }
                _state.update { it.copy(
                    ratingsUi = ratingsUi
                ) }
            }
            .launchIn(viewModelScope)
    }


    fun onAction(action: MovieProfileAction) {
        when (action) {
            is MovieProfileAction.OnExtendRatingDescription -> extendRatingDescription(action.id, action.extend)
            is MovieProfileAction.OnShowTooltip -> showTooltip(action.id, action.show)
            is MovieProfileAction.OnReviewDelete -> deleteReview(action.id)
            else -> Unit
        }
    }

    private fun extendRatingDescription(id: Int, extended: Boolean) {
        val ratingsUi = _state.value.ratingsUi.toMutableList()
        val index = ratingsUi.indexOfFirst { it.id == id }
        if (index < 0) return
        ratingsUi[index] = ratingsUi[index].copy(extended = extended)
        _state.update { it.copy(
            ratingsUi = ratingsUi
        ) }
    }

    private fun showTooltip(id: Int, show: Boolean) {
        val ratingsUi = _state.value.ratingsUi.toMutableList()
        val index = ratingsUi.indexOfFirst { it.id == id }
        if (index < 0) return
        ratingsUi[index] = ratingsUi[index].copy(tooltip = show)
        _state.update { it.copy(
            ratingsUi = ratingsUi
        ) }
    }

    private fun deleteReview(id: Int) {
       viewModelScope.launch {
           profileRepository
               .deleteRating(id)
       }
    }

}