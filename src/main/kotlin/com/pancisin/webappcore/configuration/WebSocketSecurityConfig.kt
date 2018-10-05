package com.pancisin.webappcore.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.SimpMessageType
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer

@Configuration
open class WebSocketSecurityConfig : AbstractSecurityWebSocketMessageBrokerConfigurer() {

  override fun configureInbound(messages: MessageSecurityMetadataSourceRegistry) {
//    messages.simpDestMatchers("/app/**").authenticated();

    messages
      // message types other than MESSAGE and SUBSCRIBE
      .simpTypeMatchers(SimpMessageType.CONNECT, SimpMessageType.HEARTBEAT, SimpMessageType.UNSUBSCRIBE, SimpMessageType.DISCONNECT).permitAll()
      // matches any destination that starts with /rooms/
      .simpDestMatchers("/topic/**").authenticated()
      .simpDestMatchers("/app/**").authenticated()
      // (i.e. cannot send messages directly to /topic/, /queue/)
      // (i.e. cannot subscribe to /topic/messages/* to get messages sent to
      // /topic/messages-user<id>)
      .simpTypeMatchers(SimpMessageType.MESSAGE, SimpMessageType.SUBSCRIBE).denyAll()
      // catch all
      .anyMessage().denyAll();

  }

  override fun sameOriginDisabled(): Boolean = true
}
