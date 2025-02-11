package org.ernisernis.ratemoviescmp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.ernisernis.ratemoviescmp.core.data.networking.HttpClientFactory
import org.ernisernis.ratemoviescmp.movie.data.database.DatabaseFactory
import org.ernisernis.ratemoviescmp.movie.data.database.MovieDatabase
import org.ernisernis.ratemoviescmp.movie.data.network.KtorRemoteMovieDataSource
import org.ernisernis.ratemoviescmp.movie.data.network.RemoteMovieDataSource
import org.ernisernis.ratemoviescmp.movie.data.repository.DefaultMovieRepository
import org.ernisernis.ratemoviescmp.movie.data.repository.DefaultProfileRepository
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository
import org.ernisernis.ratemoviescmp.movie.domain.ProfileRepository
import org.ernisernis.ratemoviescmp.movie.presentation.SelectedMovieViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.models.use_case.ValidateRateDescription
import org.ernisernis.ratemoviescmp.movie.presentation.models.use_case.ValidateRateNumber
import org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark.MovieBookmarkViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.MovieDetailViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.MovieListViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_profile.MovieProfileViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_rate.MovieRateViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    single { ValidateRateDescription() }
    single { ValidateRateNumber() }
    singleOf(::KtorRemoteMovieDataSource).bind<RemoteMovieDataSource>()
    singleOf(::DefaultMovieRepository).bind<MovieRepository>()
    singleOf(::DefaultProfileRepository).bind<ProfileRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<MovieDatabase>().bookmarkMovieDao }
    single { get<MovieDatabase>().ratingDao }

    viewModelOf(::MovieListViewModel)
    viewModelOf(::MovieDetailViewModel)
    viewModelOf(::MovieRateViewModel)
    viewModelOf(::SelectedMovieViewModel)
    viewModelOf(::MovieBookmarkViewModel)
    viewModelOf(::MovieProfileViewModel)
}