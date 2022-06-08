package me.vlasoff.diplombackend.repos;

import me.vlasoff.diplombackend.models.db.UniversityInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UniversityInfoRepository : JpaRepository<UniversityInfo, Long> {

    fun findByUniversityIdEquals(universityId: Int): UniversityInfo
}