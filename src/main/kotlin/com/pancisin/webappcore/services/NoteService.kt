package com.pancisin.webappcore.services

import com.pancisin.webappcore.domain.Note
import org.springframework.stereotype.Component
import java.util.*

@Component
interface NoteService {
  fun save(note: Note) : Note
  fun findById(noteId: UUID) : Note
}
