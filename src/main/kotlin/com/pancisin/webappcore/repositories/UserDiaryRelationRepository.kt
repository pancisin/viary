package com.pancisin.webappcore.repositories

import com.pancisin.webappcore.domain.UserDiaryRelation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserDiaryRelationRepository : JpaRepository<UserDiaryRelation, UUID>
