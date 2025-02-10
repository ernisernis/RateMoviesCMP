package org.ernisernis.ratemoviescmp.movie.presentation.movie_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.ernisernis.ratemoviescmp.movie.domain.ProfileRepository

class MovieProfileViewModel(
    private val profileRepository: ProfileRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MovieProfileState())
    val state = _state.asStateFlow()

    init {
        observeRatings()
    }

    private fun observeRatings() {
        profileRepository
            .getRatedMovies()
            .onEach { ratings ->
                _state.update { it.copy(
                    ratings = ratings
                ) }
            }
            .launchIn(viewModelScope)
    }


    fun onAction(action: MovieProfileAction) {
        when (action) {
            MovieProfileAction.OnTestClick -> TODO()
            else -> Unit
        }
    }

}