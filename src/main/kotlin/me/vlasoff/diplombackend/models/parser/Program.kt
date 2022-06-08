package me.vlasoff.diplombackend.models.parser

data class Program(
    val title: String,
    val exams: List<String>,
    val cost: Int?,
    val examResultsForFreePlaces: Int?,
    val freePlaces: Int?,
    val examResultsForPaidPlaces: Int?,
    val paidPlaces: Int?,
    val univerId: Int,
): Institution {
    companion object {
        fun notActual(): Program {
            return Program(
                title = "",
                exams = emptyList(),
                cost = null,
                examResultsForFreePlaces = null,
                freePlaces = null,
                examResultsForPaidPlaces = null,
                paidPlaces = null,
                univerId = -1
            )
        }

    }
}