package com.pancisin.webappcore.services

import com.pancisin.webappcore.domain.Day
import org.joda.time.DateTime
import org.springframework.stereotype.Component
import java.util.*

@Component
interface DayService {
  fun save(day: Day): Day
  fun findByDiary(diaryId: UUID): List<Day>
  fun findByDiaryAndDateSpam(diaryId: UUID, start: DateTime, end: DateTime) : List<Day>
}
