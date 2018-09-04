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
import javax.persistence.criteria.Predicate


@Component
open class DayServiceImpl : DayService {

  @Autowired
  lateinit var em: EntityManager

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

//    query.select(root).where(builder.equal(diaryAssociate.get<Diary>("id"), diaryId))

    if (start != null) {
      predicates.add(builder.greaterThan(identityAssociate.get<DayIdentity>("dateNumber").`as`(Int::class.java), start.dayOfYear))
      predicates.add(builder.equal(identityAssociate.get<DayIdentity>("year"), start.year))

//      query.where(
//        builder.greaterThan(identityAssociate.get<DayIdentity>("dateNumber").`as`(Int::class.java), start.dayOfYear),
//        builder.equal(identityAssociate.get<DayIdentity>("year"), start.year)
//      )
    }

    if (end != null) {
      predicates.add(builder.lessThan(identityAssociate.get<DayIdentity>("dateNumber").`as`(Int::class.java), end.dayOfYear))
      predicates.add(builder.equal(identityAssociate.get<DayIdentity>("year"), end.year))

//      query.where(
//        builder.lessThan(identityAssociate.get<DayIdentity>("dateNumber").`as`(Int::class.java), end.dayOfYear),
//        builder.equal(identityAssociate.get<DayIdentity>("year"), end.year)
//      )
    }

    query.select(root).where(*predicates.toTypedArray())
    return em.createQuery(query).resultList
  }
}
