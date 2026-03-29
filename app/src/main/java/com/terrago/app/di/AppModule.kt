package com.terrago.app.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.terrago.app.data.repositories.AnimalsRepositoryImpl
import com.terrago.app.data.repositories.ObjectsRepositoryImpl
import com.terrago.app.data.repositories.SpeciesRepositoryImpl
import com.terrago.app.db.TerraGoDatabase
import com.terrago.app.domain.repository.AnimalsRepository
import com.terrago.app.domain.repository.ObjectsRepository
import com.terrago.app.domain.repository.SpeciesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAnimalsRepository(
        impl: AnimalsRepositoryImpl
    ): AnimalsRepository

    @Binds
    @Singleton
    abstract fun bindSpeciesRepository(
        impl: SpeciesRepositoryImpl
    ): SpeciesRepository

    @Binds
    @Singleton
    abstract fun bindObjectsRepository(
        impl: ObjectsRepositoryImpl
    ): ObjectsRepository

    companion object {
        @Provides
        @Singleton
        fun provideSqlDriver(@ApplicationContext context: Context): SqlDriver {
            return AndroidSqliteDriver(
                schema = TerraGoDatabase.Schema,
                context = context,
                name = "TerraGoDatabase.db"
            )
        }

        @Provides
        @Singleton
        fun provideTerraGoDatabase(driver: SqlDriver): TerraGoDatabase {
            return TerraGoDatabase(driver)
        }
    }
}