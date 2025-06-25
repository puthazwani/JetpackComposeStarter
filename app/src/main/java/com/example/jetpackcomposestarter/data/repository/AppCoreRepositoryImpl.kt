package com.example.jetpackcomposestarter.data.repository

import com.example.jetpackcomposestarter.data.local.AppCoreDao
import com.example.jetpackcomposestarter.data.local.entities.TravelAuthorisationEntity

/**
 * This is the concrete implementation of the AppCoreRepository interface.
 * It connects the app's data layer (Room via AppCoreDao) to the domain/UI layers.
 */

class AppCoreRepositoryImpl (
    private val appCoreDao: AppCoreDao
): AppCoreRepository {
    override fun getTravelAuthorisations() = appCoreDao.getTAFListing()
    override suspend fun getTafFormById(id: Int) = appCoreDao.getTafFormById(id)
    override suspend fun insertTafForm(tafForm: TravelAuthorisationEntity) = appCoreDao.insertTafForm(tafForm)
}