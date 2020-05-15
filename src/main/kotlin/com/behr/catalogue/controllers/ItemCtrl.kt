package com.behr.catalogue.controllers

import com.behr.catalogue.entities.Item
import com.behr.catalogue.services.ItemServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CatalogueCtrl @Autowired constructor(
        private val itemServiceImpl: ItemServiceImpl
) {
    @GetMapping("/items")
    fun getAllItems(): List<Item> {

        return itemServiceImpl.getAllItems()
    }

    @GetMapping("/items/{itemId}")
    fun getAllItems(@PathVariable(name = "itemId") itemId: Int): ResponseEntity<Item?> {

        val requestedItem = itemServiceImpl.getItemById(itemId)
        println(itemId)
        return if (requestedItem == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(requestedItem, HttpStatus.OK)
        }
    }
}
