package com.behr.catalogue

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CatalogueApplication

fun main(args: Array<String>) {
	runApplication<CatalogueApplication>(*args)
}
