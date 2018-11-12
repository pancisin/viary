package com.pancisin.webappcore.services.impl

import com.pancisin.webappcore.domain.Contact
import com.pancisin.webappcore.services.ContactService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Component
open class ContactServiceImpl : ContactService {

  @Autowired
  lateinit var entityManager: EntityManager

  @Transactional
  override fun save(contact: Contact): Contact {
    entityManager.persist(contact)
    return contact;
  }
}
