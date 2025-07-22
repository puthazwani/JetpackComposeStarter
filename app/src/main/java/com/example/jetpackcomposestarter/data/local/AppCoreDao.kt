package com.example.jetpackcomposestarter.data.local

import androidx.room.*
import com.example.jetpackcomposestarter.data.local.entities.*
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object.
 * This is where the database CRUD operations are defined.
 */

@Dao
interface AppCoreDao {

    // Travel Authorisation
    // Parent Form
    // Insert multiple TAF application form records into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE) // If a record with the same primary key already exists, it will be replaced
    suspend fun saveTAFForm(applicationForms: List<TravelAuthorisationEntity>): List<Long>

    // Update a single existing TAF application form in the database
    @Update
    fun updateTAFForm(applicationForm: TravelAuthorisationEntity)

    @Query("SELECT * FROM taf_application_table ORDER BY id ASC")
    fun getTAFListing(): Flow<List<TravelAuthorisationEntity>>

    @Query("SELECT * FROM taf_application_table WHERE id = :id")
    suspend fun getTafFormById(id: Int): TravelAuthorisationEntity

    @Query("SELECT * FROM taf_application_table WHERE id = :id")
    suspend fun getTAFForm(id: Long): TravelAuthorisationEntity

    @Query("DELETE FROM taf_application_table WHERE id = :id")
    suspend fun deleteTAFForm(id: Long)

    // Traveller Item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTravellerItem(travellerItem: List<TafApplicationTravellerEntity>)

    @Query("SELECT * FROM taf_application_traveller_table WHERE tafId = :id")
    suspend fun getTravellerItemByFormId(id: Long): List<TafApplicationTravellerEntity>

    @Query("DELETE FROM taf_application_traveller_table WHERE itemId = :itemId")
    suspend fun deleteTravellerItemById(itemId: Long)

    @Query("DELETE FROM taf_application_traveller_table WHERE tafId = :tafId")
    suspend fun deleteAllTravellersByFormId(tafId: Long): Int

    @Transaction
    @Query("SELECT * FROM taf_application_table WHERE id = :id")
    suspend fun getTAFFormWithTravellerItem(id: Long): TravelAuthorisationWithTravellerItem

    // Itinerary Item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveItineraryItem(itineraryItems: List<TafApplicationItineraryEntity>)

    @Update
    suspend fun updateItineraryItem(itineraryItem: TafApplicationItineraryEntity)

    @Query("SELECT * FROM taf_application_itinerary_table WHERE tafId = :id")
    suspend fun getItineraryItemByFormId(id: Long): List<TafApplicationItineraryEntity>

    @Query("DELETE FROM taf_application_itinerary_table WHERE itemId = :itemId")
    suspend fun deleteItineraryItemById(itemId: Long)

    @Query(" DELETE FROM taf_application_itinerary_table WHERE tafId = :tafId")
    suspend fun deleteAllItinerariesByFormId(tafId: Long): Int

    @Transaction
    @Query("SELECT * FROM taf_application_table WHERE id = :id")
    suspend fun getTAFFormWithItineraries(id: Long): TravelAuthorisationWithItineraryItem

}