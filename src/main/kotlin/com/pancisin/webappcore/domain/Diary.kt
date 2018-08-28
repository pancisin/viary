package com.pancisin.webappcore.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.pancisin.webappcore.domain.annotations.NameIdentifier
import com.pancisin.webappcore.utils.UniqueSlugGenerator
import org.hibernate.annotations.GenerationTime
import org.hibernate.annotations.GeneratorType
import org.hibernate.annotations.GenericGenerator
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "diaries")
data class Diary(

  @NotEmpty
  @NameIdentifier
  @Column(name = "name")
  @JsonProperty("name")
  var name: String? = null,

  @Lob
  @Column(name = "description")
  @JsonProperty("description")
  var description: String? = null

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

  @Column(
    name = "slug",
    unique = true,
    updatable = false)
//    nullable = false)
  @JsonProperty(value = "slug")
  @GeneratorType(
    type = UniqueSlugGenerator::class,
    `when` = GenerationTime.INSERT)
  var slug: String? = null

  @JsonIgnore
  @OneToMany(mappedBy = "diary")
  var days: List<Day> = ArrayList()

  @JsonIgnore
  @OneToMany(mappedBy = "diary")
  var userRelations: List<UserDiaryRelation> = ArrayList()

  val priviledge: UserDiaryRelation?
    @Transient
    @JsonProperty(
      value = "priviledge",
      access = JsonProperty.Access.READ_ONLY
    )
    get() {
      if (SecurityContextHolder.getContext().authentication != null
        && SecurityContextHolder.getContext().authentication.isAuthenticated
        && SecurityContextHolder.getContext().authentication !is AnonymousAuthenticationToken) {

        val email = SecurityContextHolder.getContext().authentication.principal

        return userRelations?.firstOrNull { email.equals(it.user?.email) }
      }
      return null
    }
}
