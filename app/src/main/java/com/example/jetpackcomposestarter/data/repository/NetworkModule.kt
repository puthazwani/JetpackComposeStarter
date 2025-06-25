package com.example.jetpackcomposestarter.data.repository

import android.content.Context
import androidx.room.Room
import com.example.jetpackcomposestarter.data.local.AppCoreDao
import com.example.jetpackcomposestarter.data.local.AppCoreDatabase
import com.example.jetpackcomposestarter.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideAppCoreDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        AppCoreDatabase::class.java,
        context.resources.getString(R.string.db_name)
    ).build()

    @Provides
    fun provideAppCoreDao(
        appCoreDatabase: AppCoreDatabase
    ) = appCoreDatabase.dao

    @Provides
    fun provideAppCoreRepository(
        appCoreDao: AppCoreDao
    ): AppCoreRepository = AppCoreRepositoryImpl(
        appCoreDao = appCoreDao
    )
}