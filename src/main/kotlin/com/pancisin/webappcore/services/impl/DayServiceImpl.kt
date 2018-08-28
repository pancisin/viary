package com.pancisin.webappcore.services.impl

import com.pancisin.webappcore.domain.Day
import com.pancisin.webappcore.domain.Diary
import com.pancisin.webappcore.domain.embeddable.DayIdentity
import com.pancisin.webappcore.services.DayService
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.EntityManager

@Component
class DayServiceImpl : DayService {

  @Autowired
  lateinit var em: EntityManager

  override fun save(day: Day): Day {
    return em.persist(day) as Day
  }

  override fun findByDiary(diaryId: UUID): List<Day> {
    val builder = em.criteriaBuilder

    val query  = builder.createQuery(Day::class.java)
    val root = query.from(Day::class.java)

    val diaryAssociate = root.join<Day, Diary>("diary")

    query.select(root).where(
      builder.equal(diaryAssociate.get<Diary>("id"), diaryId)
    )

    return em.createQuery(query).resultList
  }

  override fun findByDiaryAndDateSpam(diaryId: UUID, start: DateTime, end: DateTime): List<Day> {
    val builder = em.criteriaBuilder

    val query  = builder.createQuery(Day::class.java)
    val root = query.from(Day::class.java)

    val diaryAssociate = root.join<Day, Diary>("diary")
    val identityAssociate = root.join<Day, DayIdentity>("identity")

    query.select(root).where(
      builder.equal(diaryAssociate.get<Diary>("id"), diaryId),
      builder.greaterThan(identityAssociate.get<DayIdentity>("dateNumber"), start.dayOfYear),
      builder.greaterThan(identityAssociate.get<DayIdentity>("dateNumber"), end.dayOfYear),
      builder.equal(identityAssociate.get<DayIdentity>("year"), start.year)
    )

    return em.createQuery(query).resultList
  }
}
