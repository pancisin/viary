package com.pancisin.webappcore.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component


@Component
open class UserDetailsService : org.springframework.security.core.userdetails.UserDetailsService {

  @Autowired
  lateinit var userService: UserService

  override fun loadUserByUsername(s: String): UserDetails {
    val user = userService.findByEmail(s)
    return  User(user.email, user.password, user.roles.map { SimpleGrantedAuthority(it.name) })
  }
}
