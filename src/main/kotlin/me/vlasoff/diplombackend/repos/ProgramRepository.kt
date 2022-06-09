package me.vlasoff.diplombackend.repos;

import me.vlasoff.diplombackend.models.db.Program
import org.bson.types.ObjectId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProgramRepository : MongoRepository<Program, ObjectId> {
}