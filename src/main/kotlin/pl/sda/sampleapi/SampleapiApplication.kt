package pl.sda.sampleapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleapiApplication

fun main(args: Array<String>) {
    runApplication<SampleapiApplication>(*args)
}
