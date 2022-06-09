package me.vlasoff.diplombackend.models.db

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.*

data class UniversityInfo(
    val fullName: String,
    val hasDormitory: Boolean,
    val isGovernmental: Boolean,
    val hasMilitaryDepartment: Boolean,
    val hasFreePlaces: Boolean,
    val hasLicence: Boolean,
    val description: String,
    val phoneNumber: String,
    val address: String,
    val email: String,
    val cite: String,
)
