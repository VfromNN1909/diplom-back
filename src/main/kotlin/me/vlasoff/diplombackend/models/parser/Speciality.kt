package me.vlasoff.diplombackend.models.parser

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
) : Institution {
    companion object {
        fun notActual(): Speciality {
            return Speciality(
                title = "",
                degree = "",
                studyingForms = emptyList(),
                exams = emptyList(),
                cost = null,
                examResultsForFreePlaces = null,
                freePlaces = null,
                examResultsForPaidPlaces = null,
                paidPlaces = null,
            )
        }
    }
}
