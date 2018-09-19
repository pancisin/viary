package com.pancisin.webappcore.domain

import com.pancisin.webappcore.domain.dtos.DayDto
import com.pancisin.webappcore.domain.embeddable.DayIdentity
import javax.persistence.*

@Entity
@Table(name = "days")
class Day(dateNumber: Int, year: Int, diary: Diary?) {

  @EmbeddedId
  var identity: DayIdentity? = null

  @MapsId("diaryId")
  @ManyToOne(optional = false)
  var diary: Diary? = null

  constructor() : this(0, 0, null) {
  }

  init {
    this.identity = DayIdentity(dateNumber, year, diary?.id)
    this.diary = diary
  }

  @Lob
  @Column(
    name = "content"
  )
  var content: String = ""

  @OneToMany(mappedBy = "day")
  var notes: List<Note> = ArrayList()
}
