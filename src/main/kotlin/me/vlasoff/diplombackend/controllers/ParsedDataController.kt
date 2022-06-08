package me.vlasoff.diplombackend.controllers


import me.vlasoff.diplombackend.models.parser.Program
import me.vlasoff.diplombackend.models.parser.Speciality
import me.vlasoff.diplombackend.models.parser.University
import me.vlasoff.diplombackend.models.parser.UniversityInfo
import me.vlasoff.diplombackend.services.ProgramRepository
import me.vlasoff.diplombackend.services.SpecialitiesRepository
import me.vlasoff.diplombackend.services.UniversityInfoService
import me.vlasoff.diplombackend.services.UniversityService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/univer")
class ParsedDataController {

    @GetMapping("/universities")
    fun getUniversities(): ResponseEntity<List<University>> {
        val universities = UniversityService().getUniversities()
        return if (universities.isNullOrEmpty()) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(universities)
        }
    }

    @GetMapping("/{id}/specialities")
    @ResponseBody
    fun getSpecialities(
        @PathVariable("id") id: Int,
        @RequestParam(required = false) title: String?
    ): ResponseEntity<List<Speciality>> {
        val specialities = SpecialitiesRepository(id).getUniversitySpecialities()
        return if (specialities.isNullOrEmpty()) {
            ResponseEntity.notFound().build()
        } else {
            if (!title.isNullOrEmpty()) {
                ResponseEntity.ok(specialities.filter { it.title.contains(title) })
            } else {
                ResponseEntity.ok(specialities)
            }
        }
    }

    @GetMapping("/{id}/bakspec")
    @ResponseBody
    fun getPrograms(
        @PathVariable("id") id: Int,
        @RequestParam(required = false) title: String?
    ): ResponseEntity<List<Program>> {
        val programs = ProgramRepository(id).getUniversityPrograms()
        return if (programs.isNullOrEmpty()) {
            ResponseEntity.notFound().build()
        } else {
            if (!title.isNullOrEmpty()) {
                ResponseEntity.ok(programs.filter { it.title.contains(title) })
            } else {
                ResponseEntity.ok(programs)
            }
        }
    }

    @GetMapping("/{id}/info")
    fun getUniversityInfo(@PathVariable("id") id: Int): ResponseEntity<UniversityInfo> {
        val info = UniversityInfoService(id).getUniversityInfo()
        return if (info == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(info)
        }
    }
}