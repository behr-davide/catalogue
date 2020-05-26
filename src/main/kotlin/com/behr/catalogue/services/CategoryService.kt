package com.behr.catalogue.services

import com.behr.catalogue.entities.Category

interface CategoryService {
    fun getAllCategories() : List<Category>

    fun getCategoryById(id: Int) : Category?

    fun saveCategory(category: Category) : Category

    fun deleteCategoryById(id: Int)
}
