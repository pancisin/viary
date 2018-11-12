package com.pancisin.webappcore.domain.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import com.pancisin.webappcore.domain.Contact
import com.pancisin.webappcore.domain.ContactName
import java.util.*
import javax.persistence.Column
import kotlin.collections.HashMap

data class ContactDto (

  @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "id")
  var id: UUID? = null,

  @JsonProperty("name")
  var name: ContactNameDto = ContactNameDto(),

  @JsonProperty("meta")
  var metaData: Map<String, String> = hashMapOf()

) {
  companion object {
      fun fromContact(contact: Contact) = ContactDto(
        id = contact.id,
        name = ContactNameDto.fromContactName(contact.name),
        metaData = contact.metaData
      )
  }
}

data class ContactNameDto (
  var firstName: String? = null,
  var lastName: String? = null,
  var prefix: String? = null,
  var suffix: String? = null
) {
  companion object {
      fun fromContactName(contactName: ContactName) = ContactNameDto(
        firstName = contactName.firstName,
        lastName = contactName.lastName,
        prefix = contactName.prefix,
        suffix = contactName.suffix
      )
  }
}
