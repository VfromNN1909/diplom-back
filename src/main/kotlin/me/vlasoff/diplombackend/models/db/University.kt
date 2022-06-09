package me.vlasoff.diplombackend.models.db

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

@Document(collection = "universities")
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
    val specialities: List<Speciality>,
    val programs: List<Program>,
) {
    @Id
    var id: ObjectId = ObjectId()
}