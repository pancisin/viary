package com.pancisin.webappcore.configuration

import javax.servlet.http.HttpServletResponse
import java.io.IOException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.*
import javax.servlet.http.HttpServletRequest

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter : Filter {

  @Throws(IOException::class, ServletException::class)
  override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
    val response = res as HttpServletResponse
    val request = req as HttpServletRequest
    response.setHeader("Access-Control-Allow-Origin", "*")
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
    response.setHeader("Access-Control-Max-Age", "3600")
    response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization")

    if ("OPTIONS".equals(request.getMethod(), ignoreCase = true)) {
      response.status = HttpServletResponse.SC_OK
    } else {
      chain.doFilter(req, res)
    }
  }

  override fun init(filterConfig: FilterConfig) {}

  override fun destroy() {}
}
