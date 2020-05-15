package com.behr.catalogue.services

import com.behr.catalogue.entities.Item
import com.behr.catalogue.repositories.ItemRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ItemServiceImpl @Autowired constructor(
        val itemRepo: ItemRepo
) : ItemService {

    override fun getAllItems(): List<Item> {
        return itemRepo.findAll().toList()
    }

    override fun getItemById(id: Int): Item? {
        return itemRepo.findByIdOrNull(id)
    }

    override fun saveItem(item: Item): Item {
        return itemRepo.save(item)
    }

    override fun deleteItemById(id: Int) {
        return itemRepo.deleteById(id)
    }
}