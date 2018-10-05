package com.pancisin.webappcore.domain.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import com.pancisin.webappcore.domain.Note
import com.pancisin.webappcore.domain.enums.CrudOperation
import java.util.*
import javax.validation.constraints.NotBlank

class NoteWsDto(
  @NotBlank
  @JsonProperty("content")
  var content: String? = null,

  @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "id")
  var id: UUID? = null,

  @JsonProperty("time")
  var time: String? = null
) {

  @JsonProperty("date_number")
  var dateNumber: Int? = null

  @JsonProperty("year")
  var year: Int? = null

  @JsonProperty("operation")
  var op: CrudOperation? = null

  companion object {
    fun fromNote(note: Note): NoteWsDto {

      val dto = NoteWsDto(
        id = note.id,
        content = note.content,
        time = note.time
      )

      if (note.day != null) {
        dto.dateNumber = note.day?.identity?.dateNumber ?: 0
        dto.year = note.day?.identity?.year ?: 0
      }

      return dto
    }
  }
}
