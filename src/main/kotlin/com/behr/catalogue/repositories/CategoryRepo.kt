package com.behr.catalogue.repositories

import com.behr.catalogue.entities.Category
import org.springframework.data.repository.CrudRepository

interface CategoryRepo : CrudRepository<Category, Int>