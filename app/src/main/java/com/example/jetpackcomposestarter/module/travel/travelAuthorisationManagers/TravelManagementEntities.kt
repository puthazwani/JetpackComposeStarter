package com.example.jetpackcomposestarter.module.travel.travelAuthorisationManagers

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.Date

@Entity(tableName = "travel_advance_table")
data class TravelAdvanceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val destination: String,
    val dateStart: Date,
    val dateEnd: Date,
    val totalRequested: Double,
    val totalApproved: Double
)

@Entity(
    tableName = "travel_arrangement_table",
    foreignKeys = [
        ForeignKey(
            entity = TravelAdvanceEntity::class,
            parentColumns = ["id"],     // Primary key in the parent entity
            childColumns = ["travelAdvanceId"],   // Foreign key in the child entity
            onDelete = ForeignKey.CASCADE // Ensures child records are deleted when parent is deleted
        )
    ]
)
data class TravelArrangementEntity(
    @PrimaryKey(autoGenerate = true) val itemId: Long,
    val travelAdvanceId: Long,
    val description: String,
    val rate: Double,
    val quantity: Double,
    val totalRequested: Double,
    val totalApproved: Double,
    val totalAmountRequested: Double,
    val totalAmountApproved: Double,
)

data class TravelAdvanceWithTravelArrangementItem(
    @Embedded val travelAdvanceEntity: TravelAdvanceEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "travelAdvanceId"
    )
    val travelArrangementItem: List<TravelArrangementEntity>
)


