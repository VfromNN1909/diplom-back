package me.vlasoff.diplombackend.repos;

import me.vlasoff.diplombackend.models.db.University
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UniversityRepository : JpaRepository<University, Long> {

    fun findByTitleLikeIgnoreCaseOrderByTitleDesc(title: String): List<University>

}