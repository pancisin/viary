package com.pancisin.webappcore.repositories

import com.pancisin.webappcore.domain.Note
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface NoteRepository : JpaRepository<Note, UUID> {
}
