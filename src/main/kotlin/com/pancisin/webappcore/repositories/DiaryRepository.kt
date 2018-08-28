package com.pancisin.webappcore.repositories

import com.pancisin.webappcore.domain.Diary
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DiaryRepository : JpaRepository<Diary, UUID> {
  fun findBySlug(slug: String): Optional<Diary>
}
