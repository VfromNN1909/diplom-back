package me.vlasoff.diplombackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class DiplomBackendApplication

fun main(args: Array<String>) {
    runApplication<DiplomBackendApplication>(*args)
}
