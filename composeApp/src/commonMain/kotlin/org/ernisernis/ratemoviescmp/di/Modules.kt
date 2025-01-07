package org.ernisernis.ratemoviescmp.di

import org.ernisernis.ratemoviescmp.core.data.networking.HttpClientFactory
import org.ernisernis.ratemoviescmp.movie.data.network.KtorRemoteMovieDataSource
import org.ernisernis.ratemoviescmp.movie.data.network.RemoteMovieDataSource
import org.ernisernis.ratemoviescmp.movie.data.repository.DefaultMovieRepository
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.MovieDetailViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.MovieListViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_rate.MovieRateViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteMovieDataSource).bind<RemoteMovieDataSource>()
    singleOf(::DefaultMovieRepository).bind<MovieRepository>()

    viewModelOf(::MovieListViewModel)
    viewModelOf(::MovieDetailViewModel)
    viewModelOf(::MovieRateViewModel)
}