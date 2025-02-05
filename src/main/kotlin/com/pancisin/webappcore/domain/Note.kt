package com.pancisin.webappcore.domain

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*
// import jdk.nashorn.tools.ShellFunctions.input
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


@Entity
@Table(name = "notes")
data class Note (
  @Column(name = "content")
  var content: String? = null,

  @ManyToOne(optional = false)
  var day: Day? = null
) {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(
    updatable = false,
    nullable = false
    )
  @JsonProperty("id")
  var id: UUID? = null

  val time: String?
    @Transient
    get() {
      val regex = Regex("([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9](PM|AM)?");
      val match = regex.find(content.toString())

      match?.let {
        val dateTime = DateTime.parse(it.value, DateTimeFormat.forPattern("H:m"))
        return dateTime.toString("HH:mm")
      }

      return null
    }
}
