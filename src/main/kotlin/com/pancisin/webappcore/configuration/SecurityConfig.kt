package com.pancisin.webappcore.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(-1)
open class SecurityConfig : WebSecurityConfigurerAdapter() {

  @Value("\${security.encoding-strength}")
  private val encodingStrength: Int? = null

  @Value("\${security.security-realm}")
  private val securityRealm: String? = null

  @Value("\${security.signing-key}")
  private val signingKey: String? = null

  @Autowired
  lateinit var userDetailsService: com.pancisin.webappcore.services.UserDetailsService

  @Bean
  open fun passwordEncoder(): PasswordEncoder {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder()
  }

  @Bean
  open fun accessTokenConverter(): JwtAccessTokenConverter {
    val converter = JwtAccessTokenConverter()
    converter.setSigningKey(signingKey)
    return converter
  }

  @Bean
  open fun authenticationProvider(): DaoAuthenticationProvider {
    val provider = DaoAuthenticationProvider()
    provider.setPasswordEncoder(passwordEncoder())
    provider.setUserDetailsService(userDetailsService())
    return provider
  }

  @Bean
  override fun authenticationManager(): AuthenticationManager {
    return super.authenticationManager()
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
    defaultTokenServices.setTokenEnhancer(accessTokenConverter())
    return defaultTokenServices
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
