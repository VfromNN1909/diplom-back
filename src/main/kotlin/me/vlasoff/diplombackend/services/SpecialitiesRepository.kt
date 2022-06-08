package me.vlasoff.diplombackend.services

import SpecialitiesParser
import me.vlasoff.diplombackend.models.parser.Speciality


class SpecialitiesRepository(
    private val univerId: Int
) {
    private val parser = SpecialitiesParser(univerId)

    fun getUniversitySpecialities(): List<Speciality> {
        return parser.parse()
    }

    fun getProgramsByName(name: String): List<Speciality> {
        return parser.parse().filter { it.title.contains(name) }
    }
}