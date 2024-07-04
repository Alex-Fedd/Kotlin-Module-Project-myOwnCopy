import java.util.Scanner
import kotlin.system.exitProcess

class MainMenu {
    private val scan = Scanner(System.`in`)
    private val notesMenu = NotesMenu(this, Archive("", mutableListOf()))

    private var archInd = -1
    private val menuCommandsList = mutableListOf(
        MenuCommand(
            "Создать архив",
            { createAnArchive() },
            Archive("", notesList = mutableListOf())
        ),
        MenuCommand("Выйти", { leave() }, Archive("", notesList = mutableListOf())),
    )

    private fun printTheInitialCommands() {
        println("Вы в списке архивов. Выберите нужную команду")
        for (i in 0..1) {
            println("${i + 1}. ${menuCommandsList[i].command}")
        }
    }

    fun startArchivesMenu() {
        while (true) {
            printTheInitialCommands()
            if (menuCommandsList.size > 2) {
                for (i in 2 until menuCommandsList.size) {
                    println("${i + 1}. ${menuCommandsList[i].command}")
                }
            }
            menuCheck(input = scan.nextLine(), menuCommandsList)
        }
    }


    fun menuCheck(input: String, menuCommandsList: MutableList<MenuCommand>) {

        when (input) {
            "1" -> menuCommandsList[0].commandAction()
            "2" -> menuCommandsList[1].commandAction()

            else -> {
                if (input == "" || input.toIntOrNull() == null) {
                    println("Вы ввели нечисловое значение. Введите корректный номер.")
                } else if (input.toIntOrNull() == 0 || input.toIntOrNull() !in 1..menuCommandsList.size) {
                    println("Такой команды нет. Введите корректное значение из списка.")
                } else {
                    archInd =
                        input.toInt()//если ввел "3" как пункт, то тут 3 хранится для 3-й команды под индексом списка 2 (от 0 до 2 = 3 элемента)
                    menuCommandsList[input.toInt() - 1].commandAction() // запускаю команду созданного элемента(=открыть архив по пункту), если все ок
                }
            }

        }

    }


    private fun leave() {
        exitProcess(0)
    }

    private fun createAnArchive() {
        println("Задайте название новому архиву")
        val userInput = scan.nextLine()
        if (userInput != "" && userInput != null && userInput.isNotBlank()) {
            val newArchive = Archive(userInput, mutableListOf())
            menuCommandsList.add(
                MenuCommand(
                    "Архив $userInput",
                    { openSelectedArchive(newArchive) },
                    newArchive
                )
            )
            println("Архив $userInput создан") // добавляю архив (имя и список заметок его при нем) + его команда-стринг+функция на выполнение

        } else {
            println("Архив должен иметь название (пустое значение не принимается)")
        }
    }

    private fun openSelectedArchive(archive: Archive) {
        archInd =
            menuCommandsList.indexOfFirst { it.archive == archive } //добываю номер индекса первого элемента, кот.совпадает по имени с переданным объектом
        if (archInd != -1) {
            println("Открыт архив ${archive.archiveName}.")
            //NotesMenu(this, archive).printNotesMenu()
            notesMenu.printNotesMenu(archive)
        } else {
            println("Архив с именем ${archive.archiveName} не найден.")
        }
    }
}
