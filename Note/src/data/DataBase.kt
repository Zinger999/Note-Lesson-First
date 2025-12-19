package data

import models.Category
import models.Note

class DataBase {
    private val categories = ArrayList<Category>()
    private val favorite = ArrayList<Note>()

    init {
        fillCategories()
    }

    private fun fillCategories() {
        for (i in 0 until 5) {
            categories.add(
                Category(
                    name = "category $i",
                    notes = getMockNotesList()
                )
            )
        }
    }

    private fun getMockNotesList(): ArrayList<Note> {
        val notes = ArrayList<Note>()
        for (i in 0 until 5) {
            notes.add(Note("note title $i", "note description $i", "mockDate"))
        }
        return notes
    }

    fun addCategory(category: Category) {
        categories.add(category)
    }

    fun getCategories() = categories
    fun getNotes(indexCategory: Int) = categories[indexCategory].notes
    fun getCategoryByIndex(index: Int) = categories[index]
    fun getNoteByIndex(indexCategory: Int, indexNote: Int) = categories[indexCategory].notes[indexNote]
    fun addNote(category: Category, note: Note) {
        category.notes.add(note)
    }
    fun addToFavors(note: Note) {
        favorite.add(note)
    }
    fun getFavors() = favorite
}