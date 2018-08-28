package com.pancisin.webappcore.rest.controllers.v1

import com.pancisin.webappcore.domain.Diary
import com.pancisin.webappcore.domain.dtos.DiaryDto
import com.pancisin.webappcore.domain.dtos.UserDto
import com.pancisin.webappcore.services.DiaryService
import com.pancisin.webappcore.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/user/me")
class MeController {

  @Autowired
  lateinit var userService: UserService

  @Autowired
  lateinit var diaryService: DiaryService

  @GetMapping
  fun getUserData(principal: Principal) = UserDto.fromUser(userService.findByEmail(principal.name))

  @GetMapping("/diary")
  fun getDiaries(principal: Principal) : ResponseEntity<List<DiaryDto>> {
    val stored = userService.findByEmail(principal.name)
    val diaries = diaryService.getByUser(stored.id!!).map { DiaryDto.fromDiary(it)}
    return ResponseEntity.ok(diaries)
  }

  @PostMapping("/diary")
  fun createDiary(@RequestBody @Valid diaryDto: DiaryDto, principal: Principal) : ResponseEntity<DiaryDto> {
    val stored = Diary(
      name = diaryDto.name,
      description = diaryDto.description
    )

    diaryService.create(stored, userService.findByEmail(principal.name))

    return ResponseEntity.ok(diaryDto.apply {
      slug = stored.slug
    })
  }
}
