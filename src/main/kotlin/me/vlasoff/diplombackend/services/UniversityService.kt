package me.vlasoff.diplombackend.services

import UniversitiesParser
import me.vlasoff.diplombackend.models.parser.University
import me.vlasoff.diplombackend.repos.UniversityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class UniversityService @Autowired constructor(
    private val repository: UniversityRepository
) {
    private val parser = UniversitiesParser()

    fun getUniversities(): List<University> {
        return parser.parse()
    }
}