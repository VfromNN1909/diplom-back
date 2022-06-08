package me.vlasoff.diplombackend.repos;

import me.vlasoff.diplombackend.models.db.Speciality
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpecialityRepository : JpaRepository<Speciality, Long> {

}