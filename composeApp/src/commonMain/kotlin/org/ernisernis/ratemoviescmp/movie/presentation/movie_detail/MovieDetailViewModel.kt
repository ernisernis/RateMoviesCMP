package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ernisernis.ratemoviescmp.core.domain.util.onSuccess
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository
import org.ernisernis.ratemoviescmp.movie.presentation.models.toMovieDetailsUi

class MovieDetailViewModel(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailState())
    val state = _state.asStateFlow()

    fun initData(
        id: Int,
        bannerUrl: String,
        title: String,
        imageUrl: String
    ) {
        _state.update { it.copy(
            id = id,
            bannerUrl = bannerUrl,
            title = title,
            imageUrl = imageUrl
        ) }
        viewModelScope.launch {
            movieRepository
                .getMovieDetail(id = id)
                .onSuccess { movieDetail ->
                    _state.update { it.copy(
                        movieDetailUi = movieDetail.toMovieDetailsUi()
                    ) }
                }
        }
    }

    fun onAction(action: MovieDetailAction) {
        when (action) {
            else -> Unit
        }
    }
}
