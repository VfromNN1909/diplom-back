package me.vlasoff.diplombackend.models.parser

data class University(
    val title: String, //
    val specialitiesShortDescription: String,
    val city: String, //
    val logoUrl: String, //
    val cost: Int?, //
    val examResultsForFreePlaces: Int?,//
    val freePlaces: Int?,//
    val examResultsForPaidPlaces: Int?,//
    val paidPlaces: Int?,//
    val infoLink: String, //
    val info: UniversityInfo,
    val specialities: List<Speciality> = emptyList(),
    val programs: List<Program> = emptyList(),
    val professions: List<Profession> = emptyList()
) : Institution