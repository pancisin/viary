package com.pancisin.webappcore.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.context.annotation.Primary
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(-1)
open class SecurityConfig : WebSecurityConfigurerAdapter() {

  @Value("\${security.signing-key}")
  private val signingKey: String? = null

  @Value("\${security.encoding-strength}")
  private val encodingStrength: Int? = null

  @Value("\${security.security-realm}")
  private val securityRealm: String? = null

  @Autowired
  lateinit var userDetailsService: com.pancisin.webappcore.services.UserDetailsService

  @Bean
  open fun accessTokenConverter(): JwtAccessTokenConverter {
    val converter = JwtAccessTokenConverter()
    converter.setSigningKey(signingKey)
    return converter
  }

  @Bean
  open fun tokenStore(): TokenStore {
    return JwtTokenStore(accessTokenConverter())
  }

  @Bean
  @Primary
  open fun tokenServices(): DefaultTokenServices {
    val defaultTokenServices = DefaultTokenServices()
    defaultTokenServices.setTokenStore(tokenStore())
    defaultTokenServices.setSupportRefreshToken(true)
    return defaultTokenServices
  }

  @Bean
  open fun passwordEncoder(): PasswordEncoder {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder()
  }

  @Bean
  override fun authenticationManager(): AuthenticationManager {
    return super.authenticationManager()
  }

  override fun configure(auth: AuthenticationManagerBuilder) {
    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder())
  }

  override fun configure(http: HttpSecurity) {
    http
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .httpBasic()
      .realmName(securityRealm)
      .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
      .and()
      .csrf()
      .disable()

  }
}
