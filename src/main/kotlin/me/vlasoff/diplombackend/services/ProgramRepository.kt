package me.vlasoff.diplombackend.services

import me.vlasoff.diplombackend.models.parser.Program
import me.vlasoff.diplombackend.parser.ProgramsParser

class ProgramRepository(
    private val univerId: Int
) {
    private val parser = ProgramsParser(univerId)

    fun getUniversityPrograms(): List<Program> {
        return parser.parse()
    }

    fun getProgramsByName(name: String): List<Program> {
        return parser.parse().filter { it.title.contains(name) }
    }
}