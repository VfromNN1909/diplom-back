package me.vlasoff.diplombackend.models.db

import javax.persistence.*

@Entity
@Table(name = "specialities")
data class Speciality(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val title: String,
    val degree: String,
    @Convert(converter = ListToStringConverter::class)
    val studyingForms: List<String>,
    @Convert(converter = ListToStringConverter::class)
    val exams: List<String>,
    val cost: Int?,
    val examResultsForFreePlaces: Int?,
    val freePlaces: Int?,
    val examResultsForPaidPlaces: Int?,
    val paidPlaces: Int?,
    val univerId: Long,
)
