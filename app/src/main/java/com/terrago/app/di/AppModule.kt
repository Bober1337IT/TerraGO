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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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

    @Provides
    @Singleton
    fun provideAnimalRepository(db: TerraGoDatabase): AnimalsRepository {
        return AnimalsRepositoryImpl(db)

    }

    @Provides
    @Singleton
    fun provideObjectRepository(db: TerraGoDatabase): ObjectsRepository {
        return ObjectsRepositoryImpl(db)

    }

    @Provides
    @Singleton
    fun provideSpeciesRepository(db: TerraGoDatabase): SpeciesRepository {
        return SpeciesRepositoryImpl(db)

    }
}