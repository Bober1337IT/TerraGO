package com.terrago.app.di

import android.content.Context
import androidx.room.Room
import com.terrago.app.data.local.dao.AnimalsDao
import com.terrago.app.data.local.dao.ObjectsDao
import com.terrago.app.data.local.dao.SpeciesDao
import com.terrago.app.data.local.database.TerraGoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTerraGoDatabase(
        @ApplicationContext context: Context
    ): TerraGoDatabase = Room.databaseBuilder(
        context,
        TerraGoDatabase::class.java,
        DATABASE_NAME
    )
        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()

    @Provides
    fun provideAnimalsDao(db: TerraGoDatabase): AnimalsDao = db.animalsDao()

    @Provides
    fun provideObjectsDao(db: TerraGoDatabase): ObjectsDao = db.objectsDao()

    @Provides
    fun provideSpeciesDao(db: TerraGoDatabase): SpeciesDao = db.speciesDao()

    private const val DATABASE_NAME = "terra_go_room.db"
}
