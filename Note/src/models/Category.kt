package models

data class Category(
    val name: String,
    val notes: ArrayList<Note> = ArrayList(),
)