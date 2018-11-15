package com.pancisin.webappcore.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
open class WebMvcConfig : WebMvcConfigurerAdapter() {

  override fun addViewControllers(registry: ViewControllerRegistry) {
    registry
      .addViewController("/{spring:\\w+}")
      .setViewName("forward:/index.html")
  }
}
