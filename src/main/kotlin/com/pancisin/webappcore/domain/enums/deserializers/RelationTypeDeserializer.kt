package com.pancisin.webappcore.domain.enums.deserializers

import com.pancisin.webappcore.domain.enums.RelationType
import org.codehaus.jackson.JsonParser
import org.codehaus.jackson.map.DeserializationContext
import org.codehaus.jackson.map.JsonDeserializer

class RelationTypeDeserializer : JsonDeserializer<RelationType>() {
  override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): RelationType {
    return RelationType.forProp(p0?.readValueAsTree().toString())
  }
}
