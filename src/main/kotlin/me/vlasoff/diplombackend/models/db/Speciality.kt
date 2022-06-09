package me.vlasoff.diplombackend.models.db

data class Speciality(
    val title: String,
    val degree: String,
    val studyingForms: List<String>,
    val exams: List<String>,
    val cost: Int?,
    val examResultsForFreePlaces: Int?,
    val freePlaces: Int?,
    val examResultsForPaidPlaces: Int?,
    val paidPlaces: Int?,
)
