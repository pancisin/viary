package com.pancisin.webappcore.domain.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import com.pancisin.webappcore.domain.User

data class UserDto(

  @JsonProperty("id")
  var id: Long? = null,

  @JsonProperty(
    value = "email",
    access = JsonProperty.Access.WRITE_ONLY)
  var email: String? = null,

  @JsonProperty(
    value = "password",
    access = JsonProperty.Access.WRITE_ONLY)
  var password: String? = null,

  @JsonProperty("first_name")
  var firstName: String? = null,

  @JsonProperty("last_name")
  var lastName: String? = null

) {
  companion object {
    fun fromUser(user: User): UserDto {
      return UserDto(
        id = user.id,
        firstName = user.firstName,
        lastName =  user.lastName
      )
    }
  }

  val displayName: String
    @JsonProperty("display_name", access = JsonProperty.Access.READ_ONLY)
    get() = "${this.firstName} ${this.lastName}"
}
