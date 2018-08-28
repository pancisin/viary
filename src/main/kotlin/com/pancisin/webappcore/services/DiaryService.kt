package com.pancisin.webappcore.services

import com.pancisin.webappcore.domain.Diary
import com.pancisin.webappcore.domain.User
import org.springframework.stereotype.Component
import java.util.*

@Component
interface DiaryService {
  fun findById(id: UUID): Diary
  fun findBySlug(slug: String): Diary
  fun getByUser(userId: Long) : List<Diary>
  fun save(diary: Diary): Diary

  /**
   * Used to insert new diary with owner user relation.
   * @param diary - diary instance
   * @param owner - owner user istance
   * @return newly created diary instance with user relation
   */
  fun create(diary: Diary, owner: User): Diary
}
