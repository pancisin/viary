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
import javax.transaction.Transactional
import java.util.ArrayList
import javax.persistence.NoResultException
import javax.persistence.criteria.Predicate


@Component
open class DayServiceImpl : DayService {

  @Autowired
  lateinit var em: EntityManager

  override fun findByDate(diaryId: UUID, dateNumber: Int, year: Int): Day? {
    val builder = em.criteriaBuilder

    val query  = builder.createQuery(Day::class.java)
    val root = query.from(Day::class.java)

    val diaryAssociate = root.join<Day, Diary>("diary")
    val identityAssociate = root.join<Day, DayIdentity>("identity")

    val predicates = ArrayList<Predicate>()

    query.select(root).where(
      builder.equal(diaryAssociate.get<Diary>("id"), diaryId),
      builder.equal(identityAssociate.get<DayIdentity>("dateNumber"), dateNumber),
      builder.equal(identityAssociate.get<DayIdentity>("year"), year)
    )

    try {
      return em.createQuery(query).singleResult
    } catch (e : NoResultException) {
      return null
    }
  }

  @Transactional
  override fun save(day: Day): Day {
    val stored = em.find(Day::class.java, day.identity)

    if (stored != null) {
      em.merge(day)
    } else {
      em.persist(day)
    }

    return day;
  }

  override fun findByDiary(diaryId: UUID, start: DateTime?, end: DateTime?): List<Day> {
    val builder = em.criteriaBuilder

    val query  = builder.createQuery(Day::class.java)
    val root = query.from(Day::class.java)

    val diaryAssociate = root.join<Day, Diary>("diary")
    val identityAssociate = root.join<Day, DayIdentity>("identity")

    val predicates = ArrayList<Predicate>()

    predicates.add(builder.equal(diaryAssociate.get<Diary>("id"), diaryId))

    if (start != null) {
      predicates.add(builder.greaterThanOrEqualTo(identityAssociate.get<DayIdentity>("dateNumber").`as`(Int::class.java), start.dayOfYear))
      predicates.add(builder.equal(identityAssociate.get<DayIdentity>("year"), start.year))
    }

    if (end != null) {
      predicates.add(builder.lessThanOrEqualTo(identityAssociate.get<DayIdentity>("dateNumber").`as`(Int::class.java), end.dayOfYear))
      predicates.add(builder.equal(identityAssociate.get<DayIdentity>("year"), end.year))
    }

    query.select(root).where(*predicates.toTypedArray())
    return em.createQuery(query).resultList
  }
}
