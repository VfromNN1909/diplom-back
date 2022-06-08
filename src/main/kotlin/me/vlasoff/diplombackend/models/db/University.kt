package me.vlasoff.diplombackend.models.db

import javax.persistence.*

@Entity
@Table(name = "university")
data class University(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
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
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "universityId", referencedColumnName = "id")
    val info: UniversityInfo,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "univerId",  referencedColumnName = "id")
    val specialities: List<Speciality>,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "univerId",  referencedColumnName = "id")
    val programs: List<Program>,
)