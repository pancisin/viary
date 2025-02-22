package com.pancisin.webappcore.rest.controllers.v1

import com.pancisin.webappcore.domain.*
import com.pancisin.webappcore.domain.dtos.*
import com.pancisin.webappcore.domain.embeddable.DayIdentity
import com.pancisin.webappcore.domain.enums.CrudOperation
import com.pancisin.webappcore.services.ContactService
import com.pancisin.webappcore.services.DayService
import com.pancisin.webappcore.services.DiaryService
import com.pancisin.webappcore.services.NoteService
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/diary/{diaryIdentifier}")
class DiaryController {

  @Autowired
  lateinit var diaryService: DiaryService

  @Autowired
  lateinit var dayService: DayService

  @Autowired
  lateinit var noteService: NoteService

  @Autowired
  lateinit var contactService: ContactService

  @Autowired
  lateinit var websocket: SimpMessagingTemplate

  private fun findDiary(diaryIdentifier: String): Diary {
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

  @PutMapping
  fun putDiary(
    @PathVariable(name = "diaryIdentifier") diaryIdentifier: String,
    @RequestBody diaryDto: DiaryDto
  ): ResponseEntity<DiaryDto> {
    val diary = findDiary(diaryIdentifier).apply {
      name = diaryDto.name
      description = diaryDto.description
    }

    diaryService.save(diary)
    return ResponseEntity.ok(DiaryDto.fromDiary(diary))
  }

  @PostMapping("/day")
  fun postDiaryDay(
    @PathVariable(name = "diaryIdentifier") diaryIdentifier: String,
    @RequestBody dayDto: DayDto
  ): ResponseEntity<DayDto> {
    val stored = findDiary(diaryIdentifier)

    val day = Day(dateNumber = dayDto.dateNumber, year = dayDto.year, diary = stored)

    dayService.save(day)
    return ResponseEntity.ok(dayDto)
  }

  @PostMapping("/day/{dateNumber}/{year}")
  fun postDiaryDayByDate(
    @PathVariable(name = "diaryIdentifier") diaryIdentifier: String,
    @PathVariable(name = "dateNumber", required = true) dateNumber: Int,
    @PathVariable(name = "year", required = true) year: Int,
    @RequestBody dayDto: DayDto
  ): ResponseEntity<DayDto> {
    val stored = findDiary(diaryIdentifier)

    val day = Day(
      dateNumber = dateNumber,
      year = year,
      diary = stored
    )

    dayService.save(day)
    return ResponseEntity.ok(DayDto.fromDay(day))
  }

  @PostMapping("/day/{dateNumber}/{year}/note")
  fun postNoteToDay(
    @PathVariable(name = "diaryIdentifier") diaryIdentifier: String,
    @PathVariable(name = "dateNumber", required = true) dateNumber: Int,
    @PathVariable(name = "year", required = true) year: Int,
    @RequestBody @Valid note: NoteDto
  ): ResponseEntity<NoteDto> {
    val storedDiary = findDiary(diaryIdentifier)
    var storedDay = dayService.findByDate(storedDiary.id!!, dateNumber, year)

    if (storedDay == null) {
      storedDay = Day(dateNumber, year, storedDiary)
      dayService.save(storedDay)
    }

    val stored = Note(content = note.content, day = storedDay)
    noteService.save(stored)

    val noteWsDto = NoteWsDto.fromNote(stored).apply {
      op = CrudOperation.CREATE
    }
    websocket.convertAndSend("/topic/${storedDiary.slug}", noteWsDto)
    return ResponseEntity.ok(NoteDto.fromNote(stored))
  }

  @GetMapping("/day")
  fun getDiaryDays(

    @PathVariable(name = "diaryIdentifier")
    diaryIdentifier: String,

    @RequestParam(
      name = "from",
      required = false)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    fromTime: DateTime?,

    @RequestParam(
      name = "to",
      required = false)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    toTime: DateTime?

  ): ResponseEntity<List<DayDto>> {
    val stored = findDiary(diaryIdentifier)

    stored.id?.let {
      return ResponseEntity.ok(dayService.findByDiary(it, fromTime, toTime).map { d -> DayDto.fromDay(d) })
    }

    throw Exception("dsda")
  }

  @GetMapping("/contact")
  fun getContacts(
    @PathVariable(name = "diaryIdentifier") diaryIdentifier: String
  ) : ResponseEntity<List<ContactDto>> {
    val stored = findDiary(diaryIdentifier)
    return ResponseEntity.ok(stored.contacts.map { ContactDto.fromContact(it) })
  }

  @PostMapping("/contact")
  fun postContact(
    @RequestBody contact: ContactDto,
    @PathVariable(name = "diaryIdentifier") diaryIdentifier: String
  ) : ResponseEntity<ContactDto> {
    val stored = findDiary(diaryIdentifier)

    val contactToStore = Contact().apply {
      name = ContactName(
        firstName = contact.name.firstName,
        lastName = contact.name.lastName,
        prefix = contact.name.prefix,
        suffix = contact.name.suffix
      )
      diary = stored
      metaData = contact.metaData.toMutableMap()
    }

    contactService.save(contactToStore)
    return ResponseEntity.ok(ContactDto.fromContact(contactToStore))
  }
}
