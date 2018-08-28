package com.pancisin.webappcore.rest.controllers.v1

import com.pancisin.webappcore.domain.Day
import com.pancisin.webappcore.domain.Diary
import com.pancisin.webappcore.domain.dtos.DayDto
import com.pancisin.webappcore.domain.dtos.DiaryDto
import com.pancisin.webappcore.services.DayService
import com.pancisin.webappcore.services.DiaryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigureOrder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/diary/{diaryIdentifier}")
class DiaryController {

  @Autowired
  lateinit var diaryService: DiaryService

  @Autowired
  lateinit var dayService: DayService

  private fun findDiary(diaryIdentifier: String) : Diary {
    try {
      return diaryService.findById(UUID.fromString(diaryIdentifier))
    } catch (ex: IllegalArgumentException) {
      return diaryService.findBySlug(diaryIdentifier)
    }
  }

  @GetMapping
  fun getDiary(
    @PathVariable(name = "diaryIdentifier") diaryIdentifier: String
  ): ResponseEntity<DiaryDto> {
    val diary = findDiary(diaryIdentifier)
    return ResponseEntity.ok(DiaryDto(
      name = diary.name.toString(),
      description = diary.description.toString(),
      slug = diary.slug.toString()
    ))
  }

  @PostMapping("/day")
  fun postDiaryDay(
    @PathVariable(name = "diaryIdentifier") diaryIdentifier: String,
    @RequestBody dayDto: DayDto
  ): ResponseEntity<DayDto> {
    val stored = findDiary(diaryIdentifier)

    val day = Day(dateNumber = dayDto.dateNumber, year = dayDto.year, diary = stored).apply {
      content = dayDto.content.toString()
    }

    dayService.save(day)
    return ResponseEntity.ok(dayDto)
  }

  @GetMapping("/day")
  fun getDiaryDays(
    @PathVariable(name = "diaryIdentifier") diaryIdentifier: String
  ) : ResponseEntity<List<DayDto>> {
    val stored = findDiary(diaryIdentifier)

    stored.id?.let {
      return ResponseEntity.ok(dayService.findByDiary(it).map { d -> DayDto.fromDay(d) })
    }

    throw Exception("dsda")
  }
}
