package com.pancisin.webappcore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan
open class DiaryApplication {

}

fun main(args: Array<String>) {
  runApplication<DiaryApplication>(*args)
}
