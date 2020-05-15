package com.behr.catalogue.services

import com.behr.catalogue.entities.Item
import java.util.*

interface ItemService {
    fun getAllItems() : List<Item>

    fun getItemById(id: Int) : Item?

    fun saveItem(item: Item) : Item

    fun deleteItemById(id: Int)
}