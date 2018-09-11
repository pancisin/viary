package com.pancisin.webappcore.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Order(-1)
@Configuration
open class TokenEndpointSecurity : WebSecurityConfigurerAdapter()  {

  override fun configure(http : HttpSecurity) {
    http
      .authorizeRequests()
      .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
  }
}
