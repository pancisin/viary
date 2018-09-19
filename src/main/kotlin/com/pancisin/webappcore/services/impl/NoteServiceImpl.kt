package com.pancisin.webappcore.services.impl

import com.pancisin.webappcore.domain.Note
import com.pancisin.webappcore.repositories.NoteRepository
import com.pancisin.webappcore.services.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Component
open class NoteServiceImpl : NoteService {

  @Autowired
  lateinit var em: EntityManager

  @Autowired
  lateinit var noteRepository: NoteRepository

  @Transactional
  override fun save(note: Note) : Note {
    var stored: Note? = null

    if (note.id != null) {
      stored = em.find(Note::class.java, note.id)
    }

    if (stored != null) {
      em.merge(note)
    } else {
      em.persist(note)
    }

    return note;
  }

  override fun findById(noteId: UUID) = noteRepository.getOne(noteId)
  override fun deleteNote(note: Note) = noteRepository.delete(note)
}
