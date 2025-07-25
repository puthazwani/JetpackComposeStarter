package com.example.jetpackcomposestarter.data.local.entities

import androidx.room.*
import com.example.jetpackcomposestarter.module.travel.tafmanagers.TravelAuthorization
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//Travel Authorisation
//TAF Form
@Entity(tableName = "taf_application_table")
data class TravelAuthorisationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val employeeId: Long,
    val employeeNo: String = "",
    val personId: Long?,
    val localOversea: String?,
    val destination: String,
    val dateStart: Date,
    val dateEnd: Date,
    val inbound: String?,
    val outbound: String?,
    val transport: String?,
    val remarks: String?,
    val dateCreated: Date,
    val createdBy: Long,
    val dateModified: Date,
    val modifiedBy: Long,
    val dateSubmitted: Date?,
    val submittedByPersonId: Long?,
    val actionedByPersonId: Long? = null,
    val applicationSource: String,
    val applicationStatus: String,
    val applicationReferenceNumber: String = "",
    val currentApprovers: String = "",
    val dateCurrentApprovalStart: Date? = null,
    val dateWorkflowCompleted: Date? = null,
    val workflowComment: String = "",
)
//1-M Relationship (Parent Form - Attachment)
data class TravelAuthorisationWithAttachment(
    @Embedded val tafAuthorisationEntity: TravelAuthorisationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "docId"
    )
    val attachment: BaseAttachment?
)

//Traveller Item
@Entity(
    tableName = "taf_application_traveller_table",
    foreignKeys = [
        ForeignKey(
            entity = TravelAuthorisationEntity::class, // Reference the parent entity
            parentColumns = ["id"], // Primary key in the parent entity
            childColumns = ["tafId"], // Foreign key in the child entity
            onDelete = ForeignKey.CASCADE // Ensures child records are deleted when parent is deleted
        )
    ],
    indices = [Index(value = ["tafId"])]
)
data class TafApplicationTravellerEntity(
    @PrimaryKey(autoGenerate = true) val itemId: Long = 0,
    val tafId: Long, // Foreign key to TAFApplicationFormEntity
    val employeeId: Long,
    val employeeNo: String,
    val photo: String?,
    val name: String,
    val division: String,
    val department: String,
    val status: String,
    val icNumber: String,
    val passportNumber: String,
    val datePassportExpired: Date?,
    val dateOfBirth: Date?,
    val mobileNo: String,
    val isDeleted: Boolean,
    val new: Boolean,
)
//1-M Relationship (Parent Form - TravellerItem)
data class TravelAuthorisationWithTravellerItem(
    @Embedded val tafAuthorisationEntity: TravelAuthorisationEntity,
    @Relation(
        parentColumn = "id", // Primary key in the parent entity
        entityColumn = "tafId" // Foreign key in the child entity
    )
    val travellerItem: List<TafApplicationTravellerEntity> // List of item associated with parent form
)

//ItineraryItem
@Entity(
    tableName = "taf_application_itinerary_table",
    foreignKeys = [
        ForeignKey(
            entity = TravelAuthorisationEntity::class,
            parentColumns = ["id"],
            childColumns = ["tafId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TafApplicationItineraryEntity(
    @PrimaryKey(autoGenerate = true) val itemId: Long = 0,
    val tafId: Long,
    val dateStart: Date?,
    val dateEnd: Date?,
    val personId: Long?,
    val location: String?,
    val purpose: String?,
    val hotel: String?,
    val hotelContact: String?,
    val countryCode: String?,
    val hotelRate: String?,
    val hotelDurationStay: String?,
    val lateCheckout: Boolean,
    val type: String?,
    val travelAgent: String?,
    val airlines: String?,
    val departFrom1: String?,
    val destination1: String?,
    val baggage1: Int?,
    val departFrom2: String?,
    val destination2: String?,
    val baggage2: Int?,
    val etd: Date?,
    val estimateDeparture: String?,
    val eta: Date?,
    val estimateArrival: String?,
    val checkInDate: Date?,
    val checkOutDate: Date?,
    val new: Boolean,
    val edited: Boolean,
    val deleted: Boolean,
)
//1-M Relationship (Parent Form - ItineraryItem)
data class TravelAuthorisationWithItineraryItem(
    @Embedded val tafAuthorisationEntity: TravelAuthorisationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "tafId"
    )
    val itineraryItem: List<TafApplicationItineraryEntity>
)
//1-M Relationship (ItineraryItem - Attachment)
data class ItineraryItemWithAttachment(
    @Embedded val tafApplicationItineraryEntity: TafApplicationItineraryEntity,
    @Relation(
        parentColumn = "itemId",
        entityColumn = "docId"
    )
    val attachment: BaseAttachment?
)

class Converters {

    @TypeConverter
    fun fromDate(date: Date?): String? {
        return date?.let {
            val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.getDefault())
            dateFormat.format(it)
        }
    }

    @TypeConverter
    fun toDate(date: String?): Date? {
        return date?.let {
            val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.getDefault())
            dateFormat.parse(it)
        }
    }
}

fun TravelAuthorisationEntity.toModel() = TravelAuthorization(
    id = this.id,
    employeeId = this.employeeId,
    employeeNo = this.employeeNo,
    name = null,
    localOversea = this.localOversea,
    destination = this.destination,
    dateStart = this.dateStart,
    dateEnd = this.dateEnd,
    transport = this.transport ?: "",
    cashAdvance = null,
    advanceAmount = null,
    remarks = this.remarks ?: "",
    personId = this.personId ?: 0L,
    dateCreated = this.dateCreated,
    createdBy = this.createdBy,
    dateModified = this.dateModified,
    modifiedBy = this.modifiedBy,
    dateSubmitted = this.dateSubmitted,
    submittedByPersonId = this.submittedByPersonId,
    applicationSource = this.applicationSource,
    applicationStatus = this.applicationStatus,
    applicationReferenceNumber = this.applicationReferenceNumber,
    currentApprovers = this.currentApprovers,
    dateCurrentApprovalStart = this.dateCurrentApprovalStart,
    dateWorkflowCompleted = this.dateWorkflowCompleted,
    approverComments = this.workflowComment,
    remarksBackOffice = null,
    inbound = this.inbound,
    outbound = this.outbound,
    booked = null,
    bookedDate = null,
    chargedToCompanyId = null,
    chargedToCostCenterId = null,
    returnToOffice = null,
    applicationFolderId = null,
    miscText1 = null,
    miscText2 = null,
    miscText3 = null,
    miscBigText1 = null,
    miscBigText2 = null,
    miscBigText3 = null,
    miscNum1 = null,
    miscNum2 = null,
    miscNum3 = null,
    miscDate1 = null,
    miscDate2 = null,
    miscDate3 = null,
    miscBool1 = null,
    miscBool2 = null,
    miscBool3 = null
)
