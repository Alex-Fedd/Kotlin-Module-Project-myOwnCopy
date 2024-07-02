import java.util.Scanner
import kotlin.system.exitProcess


fun main() {
    println("Запускаем приложение \"Заметки\"")
    val startApp = MainMenu()
    startApp.startArchivesMenu()
}


class MainMenu {
    private val scan = Scanner(System.`in`)
    private val archivesList = mutableListOf<Archive>()
    private val menuCommandsList = mutableListOf(
        MenuCommand("Создать архив") { createAnArchive() },
        MenuCommand("Выйти") { leave() },
    )

    private fun printTheInitialCommands() {
        println("Вы в списке архивов. Выберите нужную команду")
        for (i in 0..1) {
            println("$i. ${menuCommandsList[i].command}")
        }
    }

    fun startArchivesMenu() {
        while (true) {
            printTheInitialCommands()
            if (menuCommandsList.size > 2) {
                for (i in 2 until menuCommandsList.size) {
                    println("$i. ${menuCommandsList[i].command}")
                }
            }
            menuCheck(input = scan.nextLine(), menuCommandsList)
        }
    }


    fun menuCheck(input: String, menuCommandsList: MutableList<MenuCommand>) {

            when (input) {
                "0" -> menuCommandsList[0].commandAction()
                "1" -> menuCommandsList[1].commandAction()
                else -> {
                    if (input == "" || input.toIntOrNull() == null) {
                        println("Вы ввели нечисловое значение. Введите корректный номер.")
                    } else if (input.toIntOrNull() !in 0 until menuCommandsList.size) {
                        println("Такой команды нет. Введите корректное значение из списка.")
                    } else {
                        menuCommandsList[input.toInt()].commandAction() // запускаю команду созданного элемента, если все ок
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
        if(userInput != "" && userInput != null && userInput.isNotBlank()){
            archivesList.add(Archive(userInput, mutableListOf<Note>()))
            menuCommandsList.add(MenuCommand("Архив $userInput") { openSelectedArchive(userInput) })
            println("Архив $userInput создан")
            for(i in archivesList){ println(i.archiveName) } //check for printed archives
        } else {
            println("Архив должен иметь название (пустое значение не принимается)")
        }
    }

    private fun openSelectedArchive(archiveName: String) {
        println("Открыт архив $archiveName.")
        NotesMenu(this).printNotesMenu()
    }

}


class NotesMenu(private val referenceToMainMenu: MainMenu){ // создаю функции заметок и пытаюсь вернуться, сохрнаить и тд, взаимодейст с архивом
    private val notesList = mutableListOf<Note>()
    private val scanner = Scanner(System.`in`)
    private val menuCommandsList = mutableListOf(
        MenuCommand("Создать заметку") { createANote() },
        MenuCommand("Вернуться") { leave() },
    )

    private fun leave() {
        referenceToMainMenu.startArchivesMenu()
    }


    private fun menuChecker() {
        val input = scanner.nextLine()
        referenceToMainMenu.menuCheck(input = input, menuCommandsList)
        // createANote()
    }


    private fun createANote() { // тут создаю заметку и даю ей название и (держу связь с архивом ?)
        println("Задайте название новой заметке")
        val userInput = scanner.nextLine()
        if(userInput != "" && userInput != null && userInput.isNotBlank()){

            println("Введите свой текст для заметки")
            val contentInputForNote = scanner.nextLine()
            if(contentInputForNote == null || contentInputForNote == ""){
               println("Заметка не может быть пустой")
            }
            val myNote = Note(contentInputForNote)
            notesList.add(myNote)
            menuCommandsList.add(MenuCommand("Заметка $userInput") { openTheNote(userInput) })
            println("Заметка $userInput создана")
            for(i in notesList){ println(i.noteName) } //check for printed archives
        } else {
            println("Заметка должна иметь название (пустое значение не принимается)")
        }
    }


    private fun openTheNote(input: String){

    }


    fun printNotesMenu(){
       while(true){
           println("Выберите заметку или создайте новую, выбрав нужную команду.")
           for(i in 0..1){
               println("$i. ${menuCommandsList[i].command}")
           }
           if (menuCommandsList.size > 2) {
               for (i in 2 until menuCommandsList.size) {
                   println("$i. ${menuCommandsList[i].command}")
               }
           }

           menuChecker()
       }
    }

}

class Archive(val archiveName: String, val notesList: MutableList<Note>)

class Note(val noteName: String){
    val noteData: String = ""
}

class MenuCommand(val command: String, val commandAction: () -> Unit)


