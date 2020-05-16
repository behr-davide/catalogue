package com.behr.catalogue.controllers

import com.behr.catalogue.entities.Item
import com.behr.catalogue.services.ItemServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@RestController
class CatalogueCtrl @Autowired constructor(
        private val itemServiceImpl: ItemServiceImpl,
        private val logger: Logger = LoggerFactory.getLogger(CatalogueCtrl::class.java)
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
    fun createItem(@RequestBody requestItem: Item): ResponseEntity<Item?> {
        return try {
            logger.info("Attempting to insert an item")
            val insertedItem = itemServiceImpl.saveItem(requestItem)
            logger.info("Inserted item with name: ${insertedItem.name} and id: ${insertedItem.id}")
            ResponseEntity(insertedItem, HttpStatus.CREATED)
        } catch (e: Exception) {
            logger.error("Unable to insert item", e)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/items/{itemId}")
    fun deleteItem(@PathVariable(name = "itemId") itemId: Int): ResponseEntity<String> {
        return try {
            logger.info("Attempting to delete an item")
            itemServiceImpl.deleteItemById(itemId)
            logger.info("Deleted item with id: $itemId")
            ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            logger.error("Unable to delete item", e)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
