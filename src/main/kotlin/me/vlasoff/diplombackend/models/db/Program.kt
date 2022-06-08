package me.vlasoff.diplombackend.models.db

import javax.persistence.*

@Entity
@Table(name = "program")
data class Program(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val title: String,
    @Convert(converter = ListToStringConverter::class)
    val exams: List<String>,
    val cost: Int?,
    val examResultsForFreePlaces: Int?,
    val freePlaces: Int?,
    val examResultsForPaidPlaces: Int?,
    val paidPlaces: Int?,
    val univerId: Int,
)