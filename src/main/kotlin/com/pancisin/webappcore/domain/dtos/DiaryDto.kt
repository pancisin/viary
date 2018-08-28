package com.pancisin.webappcore.domain.dtos

import com.pancisin.webappcore.domain.Diary

data class DiaryDto(
  val name: String,
  val description: String,
  var slug: String? = null
) {
  companion object {
    fun fromDiary(diary: Diary) = DiaryDto(
      name = diary.name.toString(),
      description = diary.description.toString(),
      slug = diary.slug.toString()
    )
  }
}
