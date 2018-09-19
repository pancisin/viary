package com.pancisin.webappcore.rest.controllers.v1

import com.pancisin.webappcore.domain.Note
import com.pancisin.webappcore.domain.dtos.NoteDto
import com.pancisin.webappcore.services.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/note/{noteId}")
class NoteController {

  @Autowired
  lateinit var noteService: NoteService

  @PutMapping
  fun updateNote(
    @RequestBody @Valid note: NoteDto,
    @PathVariable(name = "noteId") noteId: UUID
  ) : ResponseEntity<NoteDto> {
    val stored = noteService.findById(noteId).apply {
      content = note.content
    }

    noteService.save(stored)
    return ResponseEntity.ok(NoteDto.fromNote(stored))
  }

  @DeleteMapping
  fun deleteNote (@PathVariable(name = "noteId") noteId: UUID) : ResponseEntity<String> {
    val stored = noteService.findById(noteId)
    noteService.deleteNote(stored)
    return ResponseEntity.ok("")
  }
}
