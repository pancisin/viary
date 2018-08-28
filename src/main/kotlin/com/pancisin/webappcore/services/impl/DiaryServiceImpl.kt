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
import java.util.*
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Component
open class DiaryServiceImpl : DiaryService {

  @Autowired
  lateinit var diaryRepository: DiaryRepository

  @Autowired
  lateinit var userDiaryRelationRepository: UserDiaryRelationRepository

  @Autowired
  lateinit var em: EntityManager

  @Transactional
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

  override fun getByUser(userId: Long): List<Diary> {
    val builder = em.criteriaBuilder

    val query  = builder.createQuery(Diary::class.java)
    val root = query.from(Diary::class.java)

    val relationAssociation = root.join<Diary, UserDiaryRelation>("userRelations")
    val userAssociation = relationAssociation.join<UserDiaryRelation, User>("user")

    query.select(root).where(
      builder.equal(relationAssociation.get<UserDiaryRelation>("relation"), RelationType.OWNER),
      builder.equal(userAssociation.get<User>("id"), userId)
    )

    return em.createQuery(query).resultList
  }
}
