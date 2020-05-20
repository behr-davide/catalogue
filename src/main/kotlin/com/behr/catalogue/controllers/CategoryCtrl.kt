package com.behr.catalogue.controllers

import com.behr.catalogue.entities.Category
import com.behr.catalogue.services.CategoryServiceImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CategoryCtrl @Autowired constructor(
        private val categoryServiceImpl: CategoryServiceImpl,
        private val logger: Logger = LoggerFactory.getLogger(CategoryCtrl::class.java)
) {
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    fun getAllCategories(): List<Category> {

        return categoryServiceImpl.getAllCategories()
    }

    @GetMapping("/categories/{categoryId}")
    fun getAllCategories(@PathVariable(name = "categoryId") categoryId: Int): ResponseEntity<Category?> {

        val requestedCategory = categoryServiceImpl.getCategoryById(categoryId)

        return if (requestedCategory == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(requestedCategory, HttpStatus.OK)
        }
    }

    @PostMapping("/categories")
    fun createCategory(@RequestBody requestCategory: Category): ResponseEntity<Category?> {
        return try {
            logger.info("Attempting to insert a category")
            val insertedCategory = categoryServiceImpl.saveCategory(requestCategory)
            logger.info("Inserted category with name: ${insertedCategory.name} and id: ${insertedCategory.id}")
            ResponseEntity(insertedCategory, HttpStatus.CREATED)
        } catch (e: Exception) {
            logger.error("Unable to insert category", e)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/categories/{categoryId}")
    fun deleteCategory(@PathVariable(name = "categoryId") categoryId: Int): ResponseEntity<String> {
        return try {
            logger.info("Attempting to delete a category")
            categoryServiceImpl.deleteCategoryById(categoryId)
            logger.info("Deleted category with id: $categoryId")
            ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            logger.error("Unable to delete category", e)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}