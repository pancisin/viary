package com.pancisin.webappcore.services.impl

import com.pancisin.webappcore.domain.User
import com.pancisin.webappcore.repositories.UserRepository
import com.pancisin.webappcore.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserServiceImpl : UserService {

  @Autowired
  private lateinit var userRepository: UserRepository

  override fun findByEmail(email: String): User {
    userRepository.findByEmail(email)?.let { return it }
    throw UsernameNotFoundException("The username ${email} doesn't exist")
  }

  override fun findById(id: Long): User {
    val opt = userRepository.findById(id)

    if (opt.isPresent) {
      return opt.get()
    }

    throw UsernameNotFoundException("The username ${id} doesn't exist")
  }

  override fun save(user: User): User {
    return userRepository.save(user)
  }
}
