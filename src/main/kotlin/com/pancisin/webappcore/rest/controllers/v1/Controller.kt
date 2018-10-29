package com.pancisin.webappcore.rest.controllers.v1

import com.pancisin.webappcore.repositories.ThemeRepository
import com.pancisin.webappcore.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
class Controller {

  @Autowired
  lateinit var themeRepository: ThemeRepository

  @Autowired
  lateinit var userService: UserService

  @GetMapping("/initial")
  fun getInitialDataSet(principal: Principal): ResponseEntity<Map<String, Any?>> {
    val user = userService.findByEmail(principal.name)

    return ResponseEntity.ok(hashMapOf<String, Any?>(
      "themes" to themeRepository.findAll(),
      "preferences" to user.preferences
//      "preferences" to
      // "nodeConstraints" to NodeConstraint.values(),
      // "relationTypes" to RelationType.values(),
      // "botStepTypes" to BotStepType.values()
    ))
  }
}
