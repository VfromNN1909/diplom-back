package me.vlasoff.diplombackend.repos;

import me.vlasoff.diplombackend.models.db.University
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UniversityRepository : MongoRepository<University, ObjectId> {

}