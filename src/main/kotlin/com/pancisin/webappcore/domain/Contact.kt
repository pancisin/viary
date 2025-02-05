package com.pancisin.webappcore.domain

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "contacts")
class Contact {

  @OneToOne(
    optional = false,
    orphanRemoval = true,
    cascade = [ CascadeType.ALL ]
  )
  var name: ContactName = ContactName()

  init {
    this.name.apply {
//      firstName = identifier
    }
  }

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(
    updatable = false,
    nullable = false
    )
  var id: UUID? = null

  @ManyToOne(optional = false)
  var diary: Diary? = null

  @ElementCollection
  @MapKeyColumn(name="meta_key")
  @Column(name="value")
  var metaData: MutableMap<String, String> = hashMapOf()

  val displayName : String
    get() {
      return "${this.name.firstName} ${this.name.lastName}"
    }
}

@Entity
@Table(name = "contacts_names")
data class ContactName (

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(
    updatable = false,
    nullable = false
    )
  var id: UUID? = null,

  @Column(name = "first_name")
  var firstName: String? = null,

  @Column(name = "last_name")
  var lastName: String? = null,

  @Column(name = "prefix")
  var prefix: String? = null,

  @Column(name = "sufix")
  var suffix: String? = null
)
