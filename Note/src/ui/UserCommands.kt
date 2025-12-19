package ui

import data.database
import domain.Controller
import models.Category

class UserCommands {
    private val categoryController = CategoryController(DataBase())

    fun addCategory() {
        println("Введите название категории")
        val categoryName = readln()
        val category = Category(categoryName)
        categoryController.addCategory(category)
    }

    fun addNote() {
        if (categoryController.getCategories().isEmpty()){
            println("Сначала добавьте категорию")
            addCategory()
        }
        println("Введите название заметки")
        val name = readln()
        println("Введите описание заметки")
        val descr = readln()
        categoryController.addNote(name, descr)
    }

    fun showCategories(){
        println("Список категорий")
        categoryController.getCategoriesIndexed().forEach { indexedCategory ->
            println(indexedCategory)
        }
    }

    fun showNote(){
        val selectedCategoryIndex = categoryController.showCategoriesAndGetIndex()
        println("Список заметок")
        categoryController.getNotesIndexed(selectedCategoryIndex).forEach { indexedNote ->
            println(indexedNote)
        }
        println("Выберете номер заметки для детального просмотра: ")
        val indexNote = readln().toInt()
        categoryController.showDetailReviewNote(selectedCategoryIndex, indexNote)
    }

    fun changeCategory(){
        if (categoryController.getCategories().isEmpty()){
            println("Сначала добавьте категорию")
            addCategory()
        }
        println("___Изменение категории___")
        println("Выберите номер категории из списка:")
        showCategories()
        val categoryId = readln().toInt()

        println("1 - изменить название")
        println("2 - изменить список заметок данной категории")
        val categoryChangeCommand = readln().toInt()

        when(categoryChangeCommand){
            1 -> {
                println("Введите новое наименование категории")
                val newCategory = readln()
                categoryController.changeCategoryName(categoryId, newCategory)
            }
            2 -> {
                changeNote(categoryId)
            }
            else -> {
                println("Неверная команда")
            }

        }

    }
    fun changeNote() {
        println("Выберите номер категории из списка:")
        showCategories()
        val categoryId = readln().toInt()
        changeNote(categoryId)
    }
    fun changeNote(indexCategory: Int) {
        if (categoryController.getNotesIndexed(indexCategory).isEmpty()){
            println("Сначала добавьте заметку")
            addNote()
        }
        println("___Изменение заметки__")
        println("Выберите номер заметки из списка:")
        val notes = categoryController.getNotesIndexed(indexCategory)
        println(notes)
        val indexNote = readln().toInt()

        println("Что хотели бы изменить в заметке:")
        println("1 - заголовок")
        println("2 - текст:")
        val noteChangeCommand = readln().toInt()

        when (noteChangeCommand)
        {
            1 -> {
                println("Введите текст заголовка:")
                val text = readln()
                categoryController.changeNoteName(indexCategory, indexNote, text)
            }
            2 -> {
                println("Введите текст заметки:")
                val text = readln()
                categoryController.changeNoteDesc(indexCategory, indexNote, text)
            }
            else -> {
                println("Неверная команда")
            }
        }
    }

    fun addToFavors(){
        categoryController.addToFavors()
    }

    fun showFavors(){
        println("Список избранного:")
        categoryController.getFavors().forEach { indexedFavor ->
            println(indexedFavor)
        }
    }


}