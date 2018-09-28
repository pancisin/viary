package com.pancisin.webappcore.repositories

import com.pancisin.webappcore.domain.Theme
import org.springframework.data.jpa.repository.JpaRepository

interface ThemeRepository : JpaRepository<Theme, Long> {
}
