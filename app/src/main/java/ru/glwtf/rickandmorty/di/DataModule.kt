package ru.glwtf.rickandmorty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.glwtf.rickandmorty.data.repository.RickMortyRepositoryImpl
import ru.glwtf.rickandmorty.domain.repository.RickMortyRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindRepository(impl: RickMortyRepositoryImpl): RickMortyRepository
}