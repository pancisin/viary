package com.pancisin.webappcore.rest.controllers.v1

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class Controller {

  @GetMapping("/initial")
  fun getInitialDataSet(): ResponseEntity<Map<String, Any?>> {
    return ResponseEntity.ok(hashMapOf<String, Any?>(
      // "nodeTypes" to NodeType.values(),
      // "nodeConstraints" to NodeConstraint.values(),
      // "relationTypes" to RelationType.values(),
      // "botStepTypes" to BotStepType.values()
    ))
  }
}
