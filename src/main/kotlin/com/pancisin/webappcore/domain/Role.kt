package com.pancisin.webappcore.domain

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  val id: Long,

  @Column(name = "role_name")
  val name: String,

  @Column(name = "description")
  val description: String
)
