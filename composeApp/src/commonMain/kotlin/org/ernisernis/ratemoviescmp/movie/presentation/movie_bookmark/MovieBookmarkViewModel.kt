package org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieBookmarkViewModel : ViewModel() {

    private val _state = MutableStateFlow(MovieBookmarkState())
    val state = _state.asStateFlow()

}