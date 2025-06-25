package com.example.jetpackcomposestarter.data.repository

import com.example.jetpackcomposestarter.data.local.entities.TravelAuthorisationEntity
import kotlinx.coroutines.flow.Flow

/**
 * AppCoreRepository is an interface that defines a standard set of operations
 * for managing data within the app.
 *
 * It acts as an abstraction between the app's business logic (e.g. ViewModels)
 * and the actual data source (e.g. Room database, network, etc.).
 */

interface AppCoreRepository {

    // Travel Module
    // Travel Authorization
    fun getTravelAuthorisations(): Flow<List<TravelAuthorisationEntity>>
    suspend fun getTafFormById(id: Int): TravelAuthorisationEntity?
    suspend fun insertTafForm(tafForm: TravelAuthorisationEntity)
}