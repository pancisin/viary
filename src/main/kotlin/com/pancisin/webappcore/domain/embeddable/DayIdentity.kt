package com.pancisin.webappcore.domain.embeddable

import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class DayIdentity(

  @Column(
    name = "date_number",
    scale = 4,
    precision = 0
  )
  val dateNumber: Int? = null,

  @Column(
    name = "year",
    scale = 4,
    precision = 0
  )
  val year: Int? = null,

  var diaryId: UUID? = null

) : Serializable {
  override fun equals(other: Any?): Boolean {
    if (other is DayIdentity) return dateNumber === other.dateNumber && year === other.year && diaryId === other.diaryId
    return false;
  }

  override fun hashCode(): Int {
    return super.hashCode()
  }
}
