package com.pancisin.webappcore.domain.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import com.pancisin.webappcore.domain.Day

data class DayDto (
  @JsonProperty("date_number")
  var dateNumber: Int,

  @JsonProperty("year")
  var year: Int,

  @JsonProperty("notes")
  var notes: List<NoteDto> = ArrayList()
) {
  companion object {
    fun fromDay(day: Day) = DayDto(
      dateNumber = day.identity!!.dateNumber!!,
      year = day.identity!!.year!!,
      notes = day.notes.map { NoteDto.fromNote(it) }
    )
  }
}

