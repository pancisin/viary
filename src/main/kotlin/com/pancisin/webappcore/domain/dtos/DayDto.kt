package com.pancisin.webappcore.domain.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import com.pancisin.webappcore.domain.Day

data class DayDto (
  @JsonProperty("date_number")
  var dateNumber: Int,

  @JsonProperty("year")
  var year: Int,

  @JsonProperty("content")
  var content: String? = null
) {
  companion object {
    fun fromDay(day: Day) = DayDto(
      dateNumber = day.identity!!.dateNumber!!,
      year = day.identity!!.year!!,
      content = day.content
    )
  }
}

