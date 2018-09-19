package com.pancisin.webappcore.domain

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

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
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(
    updatable = false,
    nullable = false,
    columnDefinition = "BINARY(16)")
  @JsonProperty("id")
  var id: UUID? = null
}
