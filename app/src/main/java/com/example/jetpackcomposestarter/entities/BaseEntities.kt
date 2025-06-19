package com.example.jetpackcomposestarter.entities
//import Attachment
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attachment_table")
data class BaseAttachment(
    @PrimaryKey(autoGenerate = true) var id: Long,
    val docId: Long, // Foreign Key
    var fileName: String,
    var fileData: String, // byte[] type, adjust based on API needs
    val mineType: String = "",
    val fileExtension: String = "",
    val docType: String = "",
    val sequenceNumber: Int? = null,
    val fileSize: Int? = null,
)

//fun BaseAttachment.toAttachment(): Attachment {
//    return Attachment(
//        id = this.id,
//        docId = this.docId,
//        fileName = this.fileName,
//        fileData = this.fileData,
//        mineType = this.mineType,
//        fileExtension = this.fileExtension,
//        docType = this.docType,
//        sequenceNumber = this.sequenceNumber,
//        fileSize = this.fileSize
//    )
//}