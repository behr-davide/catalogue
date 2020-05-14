package com.behr.catalogue.repositories

import com.behr.catalogue.entities.Item
import org.springframework.data.repository.CrudRepository

interface ItemRepo : CrudRepository<Item, Int>