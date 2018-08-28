package com.pancisin.webappcore.services.impl

import com.pancisin.webappcore.domain.Diary
import com.pancisin.webappcore.domain.User
import com.pancisin.webappcore.domain.UserDiaryRelation
import com.pancisin.webappcore.domain.enums.RelationType
import com.pancisin.webappcore.exceptions.DomainObjectNotFoundException
import com.pancisin.webappcore.repositories.DiaryRepository
import com.pancisin.webappcore.repositories.UserDiaryRelationRepository
import com.pancisin.webappcore.services.DiaryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class DiaryServiceImpl : DiaryService {

  @Autowired
  lateinit var diaryRepository: DiaryRepository

  @Autowired
  lateinit var userDiaryRelationRepository: UserDiaryRelationRepository

//  @Transactional
  override fun create(diary: Diary, owner: User): Diary {
    val stored = diaryRepository.save(diary)

    val rel = UserDiaryRelation(owner, stored, RelationType.OWNER)
    userDiaryRelationRepository.save(rel)

    return stored
  }

  override fun findById(id: UUID) = diaryRepository.findById(id).orElseThrow({ DomainObjectNotFoundException("dsadas") })

  override fun findBySlug(slug: String) = diaryRepository.findBySlug(slug).orElseThrow({ DomainObjectNotFoundException("dsadas") })

  override fun save(diary: Diary): Diary {
    return diaryRepository.save(diary)
  }
}
