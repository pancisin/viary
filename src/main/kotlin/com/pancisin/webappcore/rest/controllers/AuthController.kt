package com.pancisin.webappcore.rest.controllers

import com.pancisin.webappcore.domain.User
import com.pancisin.webappcore.domain.dtos.UserDto
import com.pancisin.webappcore.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import org.springframework.security.crypto.password.PasswordEncoder

@RestController
class AuthController {

  @Autowired
  lateinit var userService: UserService

  @Autowired
  lateinit var passwordEncoder: PasswordEncoder

  @PostMapping("/api/register")
  fun register(
    @RequestBody @Valid user: UserDto,
    bindingResult: BindingResult
  ): ResponseEntity<UserDto> {

    if (bindingResult.hasErrors()) {
      throw Exception()
    }

    val data = User(
      email = user.email,
      firstName = user.firstName,
      lastName = user.lastName,
      password = passwordEncoder.encode(user.password),
      roles = ArrayList()
    )

    val stored = userService.save(data)
    return ResponseEntity.ok(UserDto.fromUser(stored));
  }
}
