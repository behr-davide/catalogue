package com.behr.catalogue.controllers

import com.behr.catalogue.entities.Item
import com.behr.catalogue.repositories.ItemRepo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CatalogueCtrl(
        private val itemRepo: ItemRepo
) {
    @GetMapping("/items")
    fun getAllItems(): List<Item> {

        return itemRepo.findAll().toList()
    }
}
