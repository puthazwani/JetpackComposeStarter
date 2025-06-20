package com.example.jetpackcomposestarter

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetpackcomposestarter.entities.*

/**
 * Main Room database class for the application.
 * Serves as main access points.
 * It defines the list of entities (tables), version, and type converters.
 */

// TODO: Setup migration strategy if schema changes in the future
// e.g., AutoMigration, manual Migration object, or fallbackToDestructiveMigration()

@Database(
    entities = [
        TravelAuthorisationEntity::class,
        TafApplicationTravellerEntity::class,
        TafApplicationItineraryEntity::class,
        BaseAttachment::class
    ],
    version = 1 // // ⚠️ Increase this when schema changes (used for migrations)
)
@TypeConverters(Converters::class) // Converts unsupported data types (e.g., Date, List) for Room
abstract class AppCoreDatabase: RoomDatabase() {
    // Exposes the DAO interface for accessing the database
    abstract val dao: AppCoreDao
}