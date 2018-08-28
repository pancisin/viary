package com.pancisin.webappcore.services

import com.pancisin.webappcore.domain.UserDiaryRelation
import org.springframework.stereotype.Component

@Component
interface UserDiaryRelationService {
  fun save(userDiaryRelation: UserDiaryRelation) : UserDiaryRelation
}
