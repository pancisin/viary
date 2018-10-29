package com.pancisin.webappcore.services

import com.pancisin.webappcore.domain.User
import org.springframework.stereotype.Component

@Component
interface UserService {
  fun findByEmail(email: String) : User
  fun findById(id: Long) : User
  fun save(user: User) : User
  fun updatePreference(email: String, preferenceKey: String, value: String?)
}
