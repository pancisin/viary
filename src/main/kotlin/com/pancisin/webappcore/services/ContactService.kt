package com.pancisin.webappcore.services

import com.pancisin.webappcore.domain.Contact
import org.springframework.stereotype.Component

@Component
interface ContactService {
  fun save(contact: Contact) : Contact
}
