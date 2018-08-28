package com.pancisin.webappcore.services

import com.pancisin.webappcore.domain.Day
import org.joda.time.DateTime
import org.springframework.stereotype.Component
import java.util.*

@Component
interface DayService {
  fun save(day: Day): Day
  fun findByDiary(diaryId: UUID): List<Day>

  /**
   * Get list od days by diary entity and filter by date span.
   * @param diaryId - UUID identifier of diary entity
   * @param start - (optional) filter date start
   * @param end - (optional) filter date end
   * @return list of days filtered by diary entity and optionally by date span
   */
  fun findByDiaryAndDateSpam(diaryId: UUID, start: DateTime?, end: DateTime?) : List<Day>
}
