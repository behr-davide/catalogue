package com.behr.catalogue.services

import com.behr.catalogue.entities.Category
import com.behr.catalogue.repositories.CategoryRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class CategoryServiceImpl @Autowired constructor(
        private val categoryRepo: CategoryRepo
) : CategoryService {
    override fun getAllCategories(): List<Category> {
        return categoryRepo.findAll().toList()
    }

    override fun getCategoryById(id: Int): Category? {
        return categoryRepo.findByIdOrNull(id)
    }

    override fun saveCategory(category: Category): Category {
        return categoryRepo.save(category)
    }

    override fun deleteCategoryById(id: Int) {
        return categoryRepo.deleteById(id)
    }
}