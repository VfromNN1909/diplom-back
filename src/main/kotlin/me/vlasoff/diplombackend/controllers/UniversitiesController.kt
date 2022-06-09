package me.vlasoff.diplombackend.controllers


import me.vlasoff.diplombackend.models.db.University
import me.vlasoff.diplombackend.repos.UniversityRepository
import me.vlasoff.diplombackend.services.UniversityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/univer")
class UniversitiesController @Autowired constructor(
    private val universityRepository: UniversityRepository
) {

    private val universityService: UniversityService = UniversityService(universityRepository)
    private val pageSize = "10"

    @GetMapping("/universities")
    fun getUniversities(
        @RequestParam page: Int,
        @RequestParam(defaultValue = "") city: String,
        @RequestParam(defaultValue = "") title: String,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<University>> {
        val pageable = PageRequest.of(page, pageSize)
        var universities = universityService.getUniversities(pageable)

        if (title != "") {
            universities = universityService.getUniversitiesByTitle(title, pageable)
        }
        if (city != "") {
            universities = universityService.getUniversitiesByCity(city, pageable)
        }
        return if (universities.isEmpty) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(universities)
        }
    }

    @GetMapping("/universitiesp")
    fun getUniversitiesByCity(
        @PathVariable(name = "city") city: String,
        @RequestParam page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<University>> {
        val pageable = PageRequest.of(page, pageSize)
        val universities = universityService.getUniversitiesByCity(city, pageable)
        return if (universities.isEmpty) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(universities)
        }
    }

    @GetMapping("/universitiesq/{title}")
    fun getUniversitiesByTitle(
        @PathVariable(name = "title") title: String,
        @RequestParam page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<University>> {
        val pageable = PageRequest.of(page, pageSize)
        val universities = universityService.getUniversitiesByTitle(title, pageable)
        return if (universities.isEmpty) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(universities)
        }
    }
}