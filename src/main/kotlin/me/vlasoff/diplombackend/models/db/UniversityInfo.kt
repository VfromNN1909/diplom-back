package me.vlasoff.diplombackend.models.db

import javax.persistence.*

@Entity
@Table(name = "univer_info")
data class UniversityInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
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
    val universityId: Int,
)
