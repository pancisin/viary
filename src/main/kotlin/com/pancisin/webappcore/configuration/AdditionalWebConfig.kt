package com.pancisin.webappcore.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

//@Configuration
open class AdditionalWebConfig {
//
//  @Bean
//  open fun corsFilter(): FilterRegistrationBean<*> {
//    val source = UrlBasedCorsConfigurationSource()
//    val config = CorsConfiguration().apply {
//      allowCredentials = true
//      addAllowedOrigin("*")
//      addAllowedHeader("*")
//      addAllowedMethod("*")
//    }
//
//    source.registerCorsConfiguration("/**", config)
//    val bean = FilterRegistrationBean(CorsFilter(source))
//    bean.setOrder(0)
//    return bean
//  }
}
