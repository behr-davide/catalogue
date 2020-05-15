package com.behr.catalogue.controllers

import com.behr.catalogue.entities.Item
import com.behr.catalogue.services.ItemServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CatalogueCtrl @Autowired constructor(
        private val itemServiceImpl: ItemServiceImpl
) {
    @GetMapping("/items")
    @ResponseStatus(HttpStatus.OK)
    fun getAllItems(): List<Item> {

        return itemServiceImpl.getAllItems()
    }

    @GetMapping("/items/{itemId}")
    fun getAllItems(@PathVariable(name = "itemId") itemId: Int): ResponseEntity<Item?> {

        val requestedItem = itemServiceImpl.getItemById(itemId)

        return if (requestedItem == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(requestedItem, HttpStatus.OK)
        }
    }

    @PostMapping("/items")
    fun createItem(@RequestBody item: Item): ResponseEntity<Item?> {
        return try {
            val insertedItem = itemServiceImpl.saveItem(item)
            ResponseEntity(insertedItem, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/items/{itemId}")
    fun deleteItem(@PathVariable(name = "itemId") itemId: Int): ResponseEntity<String> {
        return try {
            itemServiceImpl.deleteItemById(itemId)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
