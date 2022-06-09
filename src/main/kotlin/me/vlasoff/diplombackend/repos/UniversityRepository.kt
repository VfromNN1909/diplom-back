package me.vlasoff.diplombackend.repos;

import me.vlasoff.diplombackend.models.db.University
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UniversityRepository : MongoRepository<University, ObjectId> {

    override fun findAll(pageable: Pageable): Page<University>

    fun getUniversitiesByCityLikeIgnoreCase(city: String, pageable: Pageable): Page<University>

    fun getUniversitiesByTitleLikeIgnoreCase(title: String, pageable: Pageable): Page<University>

}