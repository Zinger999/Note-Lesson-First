import ui.UserCommands


fun main(args: Array<String>) {
    val userCommands = UserCommands()
    while (true) {
        println("Ввведите команду")
        println("1 - Добавить категорию")
        println("2 - Добавить заметку")
        println("3 - Список категорий")
        println("4 - Список заметок")
        println("5 - Изменить категорию")
        println("6 - Изменить заметку")
        println("7 - Добавить в избранное")
        println("8 - Показать избранное")
        println("Выберите пункт: ")
        val command = readln().toIntOrNull()
        when(command) {
            1 -> userCommands.addCategory()
            2 -> userCommands.addNote()
            3 -> userCommands.showCategories()
            4 -> userCommands.showNote()
            5 -> userCommands.changeCategory()
            6 -> userCommands.changeNote()
            7 -> userCommands.addToFavors()
            8 -> userCommands.showFavors()
            else ->  println("Неверная команда")
        }
    }
}