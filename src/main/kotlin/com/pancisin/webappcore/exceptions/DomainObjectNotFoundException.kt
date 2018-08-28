package com.pancisin.webappcore.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class DomainObjectNotFoundException(message: String) : RuntimeException(message) {
}
