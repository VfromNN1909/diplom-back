package me.vlasoff.diplombackend.models.db

data class Program(
    val title: String,
    val exams: List<String>,
    val cost: Int?,
    val examResultsForFreePlaces: Int?,
    val freePlaces: Int?,
    val examResultsForPaidPlaces: Int?,
    val paidPlaces: Int?,
)