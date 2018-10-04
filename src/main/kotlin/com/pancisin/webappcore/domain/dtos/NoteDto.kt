package com.pancisin.webappcore.domain.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import com.pancisin.webappcore.domain.Note
import java.util.*
import javax.validation.constraints.NotBlank

data class NoteDto(

  @NotBlank
  @JsonProperty("content")
  var content: String? = null,

  @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "id")
  var id: UUID? = null,

  @JsonProperty("time")
  var time: String? = null
) {
  companion object {
      fun fromNote(note: Note) = NoteDto(
        id = note.id,
        content = note.content,
        time = note.time
      )
  }
}
