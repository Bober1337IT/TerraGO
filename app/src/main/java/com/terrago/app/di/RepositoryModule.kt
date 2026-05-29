package com.terrago.app.di

import com.terrago.app.data.repository.AnimalsRepositoryImpl
import com.terrago.app.data.repository.ObjectsRepositoryImpl
import com.terrago.app.data.repository.SpeciesRepositoryImpl
import com.terrago.app.domain.animals.AnimalsRepository
import com.terrago.app.domain.objects.ObjectsRepository
import com.terrago.app.domain.species.SpeciesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAnimalsRepository(impl: AnimalsRepositoryImpl): AnimalsRepository

    @Binds
    @Singleton
    abstract fun bindObjectsRepository(impl: ObjectsRepositoryImpl): ObjectsRepository

    @Binds
    @Singleton
    abstract fun bindSpeciesRepository(impl: SpeciesRepositoryImpl): SpeciesRepository
}
