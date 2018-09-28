package com.pancisin.webappcore.domain

import javax.persistence.*

@Entity
@Table(name = "themes")
class Theme (

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  val id: Long? = null,

  @Column(name = "unsplash_url")
  val unsplashUrl: String? = null,

  @Column(name = "image_url")
  val imageUrl: String? = null,

  @Column(name = "color")
  val color: String? = null,

  @Column(name = "author")
  val author: String? = null
)
