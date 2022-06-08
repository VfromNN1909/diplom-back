package me.vlasoff.diplombackend.repos;

import me.vlasoff.diplombackend.models.db.Program
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProgramRepository : JpaRepository<Program, Long> {
}