package me.vlasoff.diplombackend.services

import me.vlasoff.diplombackend.parser.UniversitiesParser
import me.vlasoff.diplombackend.models.db.University
import me.vlasoff.diplombackend.parser.CitiesParser
import me.vlasoff.diplombackend.repos.UniversityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UniversityService @Autowired constructor(
    private val repository: UniversityRepository
) {

    fun getUniversities(): Set<University> {
        return repository.findAll().toSet()
    }
}