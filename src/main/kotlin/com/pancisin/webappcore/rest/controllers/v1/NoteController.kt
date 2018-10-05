package com.pancisin.webappcore.rest.controllers.v1

import com.pancisin.webappcore.domain.Note
import com.pancisin.webappcore.domain.dtos.NoteDto
import com.pancisin.webappcore.domain.dtos.NoteWsDto
import com.pancisin.webappcore.domain.enums.CrudOperation
import com.pancisin.webappcore.services.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/note/{noteId}")
class NoteController {

  @Autowired
  lateinit var noteService: NoteService

  @Autowired
  lateinit var websocket: SimpMessagingTemplate

  @PutMapping
  fun updateNote(
    @RequestBody @Valid note: NoteDto,
    @PathVariable(name = "noteId") noteId: UUID
  ) : ResponseEntity<NoteDto> {
    val stored = noteService.findById(noteId).apply {
      content = note.content
    }

    noteService.save(stored)

    stored.day?.diary?.slug?.let {
      val esNoteDto = NoteWsDto.fromNote(stored).apply {
        op = CrudOperation.UPDATE
      }

      websocket.convertAndSend("/topic/${it}", esNoteDto)
    }

    return ResponseEntity.ok(NoteDto.fromNote(stored))
  }

  @DeleteMapping
  fun deleteNote (@PathVariable(name = "noteId") noteId: UUID) : ResponseEntity<String> {
    val stored = noteService.findById(noteId)
    noteService.deleteNote(stored)

    stored.day?.diary?.slug?.let {
      val esNoteDto = NoteWsDto.fromNote(stored).apply {
        op = CrudOperation.DELETE
      }
      websocket.convertAndSend("/topic/${it}", esNoteDto)
    }

    return ResponseEntity.ok("")
  }
}
