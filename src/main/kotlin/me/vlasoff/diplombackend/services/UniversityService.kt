package me.vlasoff.diplombackend.services

import me.vlasoff.diplombackend.parser.UniversitiesParser
import me.vlasoff.diplombackend.models.db.University
import me.vlasoff.diplombackend.parser.CitiesParser
import me.vlasoff.diplombackend.repos.UniversityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UniversityService @Autowired constructor(
    private val repository: UniversityRepository
) {

    fun getUniversities(pageable: Pageable): Page<University> {
        return repository.findAll(pageable)
    }

    fun getUniversitiesByCity(city: String, pageable: Pageable): Page<University> {
        return repository.getUniversitiesByCityLikeIgnoreCase(city, pageable)
    }

    fun getUniversitiesByTitle(title: String, pageable: Pageable): Page<University> {
        return repository.getUniversitiesByTitleLikeIgnoreCase(title, pageable)
    }
}