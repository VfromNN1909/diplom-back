package me.vlasoff.diplombackend.controllers


import me.vlasoff.diplombackend.models.db.University
import me.vlasoff.diplombackend.repos.UniversityRepository
import me.vlasoff.diplombackend.services.UniversityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/univer")
class ParsedDataController @Autowired constructor(
    private val universityRepository: UniversityRepository
) {

    @GetMapping("/universities")
    fun getUniversities(): ResponseEntity<Set<University>> {
        val universities = UniversityService(universityRepository).getUniversities()
        return if (universities.isNullOrEmpty()) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(universities)
        }
    }
}