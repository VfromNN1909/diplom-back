package me.vlasoff.diplombackend.services

import UniversityInfoParser
import me.vlasoff.diplombackend.models.parser.UniversityInfo


class UniversityInfoService(
    private val univerId: Int
) {
    private val parser = UniversityInfoParser(univerId)

    fun getUniversityInfo(): UniversityInfo {
        return parser.parse()
    }
}