package com.pancisin.webappcore.repositories

import com.pancisin.webappcore.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
  fun findByEmail(email: String): User?
}
