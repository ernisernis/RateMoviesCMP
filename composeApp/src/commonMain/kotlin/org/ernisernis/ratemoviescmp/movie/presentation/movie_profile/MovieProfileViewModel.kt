package org.ernisernis.ratemoviescmp.movie.presentation.movie_profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieProfileViewModel : ViewModel() {

    private val _state = MutableStateFlow(MovieProfileState())
    val state = _state.asStateFlow()

    fun onAction(action: MovieProfileAction) {
        when (action) {
            MovieProfileAction.OnTestClick -> TODO()
            else -> Unit
        }
    }

}