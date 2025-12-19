package domain

import data.DataBase
import models.Category
import models.Note
import java.text.SimpleDateFormat
import java.util.*

class CategoryController(
    private val categoriesDataBase: DataBase
) {
    fun addCategory(category: Category) {
        categoriesDataBase.addCategory(category)
    }

    fun addNote(name: String, descr: String) {
        val note = Note(name, descr, getCurrentDate())
        println("Выберите номер категории для добавления")
        categoriesDataBase.getCategories().forEachIndexed { index, category ->
            println("$index ${category.name}")
        }
        val categoryIndex = readln().toInt()
        val selectedCategory = categoriesDataBase.getCategoryByIndex(categoryIndex)
        categoriesDataBase.addNote(
            selectedCategory,
            note
        )
    }

    fun getCategories() = categoriesDataBase.getCategories()

    fun showCategoriesAndGetIndex(): Int {
        getCategoriesIndexed().forEach {
            println(it)
        }
        val selectedCategory = readln().toInt()
        return selectedCategory
    }

    fun getCategoriesIndexed(): List<String> = categoriesDataBase.getCategories()
        .mapIndexed { index, category ->
            "$index ${category.name}"
        }

    fun getNotesIndexed(indexCategory: Int) = categoriesDataBase.getNotes(indexCategory)
        .mapIndexed { index, note ->
            "$index ${note.name}"
        }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd.M.yyyy HH:mm")
        val currentDate = sdf.format(Date())
        return  currentDate
    }

    fun showDetailReviewNote(indexCategory: Int, indexNote: Int) {
        val note = categoriesDataBase.getCategories()[indexCategory].notes[indexNote]
        println("Номер заметки: $indexNote")
        println("Название: ${note.name}")
        println("Описание: ${note.description}")
        println("Дата создания: ${note.date}")
    }
    fun changeCategoryName(indexCategory: Int, newCategoryName: String) {
        val categories = categoriesDataBase.getCategories()
        categories[indexCategory] = categories[indexCategory].copy(name = newCategoryName)
    }

    fun changeNoteName(indexCategory: Int, indexNote: Int, newName: String) {
        val notes = categoriesDataBase.getNotes(indexCategory)
        notes[indexNote] = notes[indexNote].copy(name = newName)
    }
    fun changeNoteDesc(indexCategory: Int, indexNote: Int, newDesc: String) {
        val notes = categoriesDataBase.getNotes(indexCategory)
        notes[indexNote] = notes[indexNote].copy(description = newDesc)
    }
    fun addToFavors(){
        println("Выберите категорию")
        categoriesDataBase.getCategories().forEachIndexed { index, category ->
            println("$index ${category.name}")
        }
        val categoryIndex = readln().toInt()
        println("Выберите заметку")
        categoriesDataBase.getNotes(categoryIndex).forEachIndexed { index, note ->
            println("$index ${note.name}")
        }
        val noteIndex = readln().toInt()
        val selectedNote = categoriesDataBase.getNoteByIndex(categoryIndex, noteIndex)
        categoriesDataBase.addToFavors(selectedNote)
    }
    fun getFavors(): List<String> = categoriesDataBase.getFavors()
        .mapIndexed { index, note ->
            "$index ${note.name}"
        }
}