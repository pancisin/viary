package com.pancisin.webappcore.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.persistence.FetchType
import javax.persistence.ManyToMany
import javax.validation.constraints.Email

@Entity
@Table(name = "users")
data class User(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  var id: Long? = null,

  @Email
  @Column(
    name = "email",
    unique = true,
    updatable = false,
    nullable = false)
  val email: String? = null,

  @Column(name = "password")
  val password: String? = null,

  @Column(name = "first_name")
  var firstName: String? = null,

  @Column(name = "last_name")
  var lastName: String? = null,

  @ManyToMany(fetch = FetchType.EAGER)
  val roles: List<Role> = ArrayList()
)
