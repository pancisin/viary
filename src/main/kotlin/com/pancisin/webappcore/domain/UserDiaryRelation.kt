package com.pancisin.webappcore.domain

import com.pancisin.webappcore.domain.enums.RelationType
import com.pancisin.webappcore.domain.enums.deserializers.RelationTypeDeserializer
import org.codehaus.jackson.annotate.JsonIgnore
import org.codehaus.jackson.annotate.JsonProperty
import org.codehaus.jackson.map.annotate.JsonDeserialize
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users_diaries_relation")
data class UserDiaryRelation(
  @JsonIgnore
  @ManyToOne(optional = false)
  var user: User? = null,

  @JsonIgnore
  @ManyToOne(optional = false)
  var diary: Diary? = null,

  @Column(name = "relation")
  @JsonProperty("relation")
  @Enumerated(EnumType.STRING)
  @JsonDeserialize(using = RelationTypeDeserializer::class)
  var relation: RelationType = RelationType.OWNER
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
