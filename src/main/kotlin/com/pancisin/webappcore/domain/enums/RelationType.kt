package com.pancisin.webappcore.domain.enums

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class RelationType private constructor(
  @JsonProperty("name")
  val prop: String,

  @JsonProperty("level")
  val level: Int,

  @JsonProperty("code")
  val code: String
) {
  OWNER("OWNER", 100, "role.owner");

  companion object {
    fun forProp(prop: String) = values().first { it.prop.equals(prop) }
  }
}
