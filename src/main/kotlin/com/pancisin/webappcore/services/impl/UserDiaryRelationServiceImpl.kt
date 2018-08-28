package com.pancisin.webappcore.services.impl

import com.pancisin.webappcore.domain.UserDiaryRelation
import com.pancisin.webappcore.repositories.UserDiaryRelationRepository
import com.pancisin.webappcore.services.UserDiaryRelationService
import org.springframework.beans.factory.annotation.Autowired

class UserDiaryRelationServiceImpl : UserDiaryRelationService {

  @Autowired
  lateinit var userDiaryRelationRepository: UserDiaryRelationRepository

  override fun save(userDiaryRelation: UserDiaryRelation): UserDiaryRelation {
    return userDiaryRelationRepository.save(userDiaryRelation)
  }
}
