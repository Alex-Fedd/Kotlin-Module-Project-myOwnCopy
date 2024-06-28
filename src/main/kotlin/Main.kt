import java.util.Scanner

//пока что общий файл - потом на классы в разные файлы разобью

fun main() {
    println("Запускаем приложение \"Заметки\"")
    val startApp = MenuSelect()
    startApp.showArchivesMenu()
}


class MenuSelect { // пока здесь логику архивов начальную и выбор экранов пишу
    private val scan = Scanner(System.`in`)

    val archivesList = mutableListOf<Archive>()
    private val screenState: CurrentScreenState? = CurrentScreenState.ArchiveSelection//меню архивов
    private val listOfCommands = mutableListOf(
        "Создать архив",
        "Выйти"
    )

    fun showArchivesMenu(): CurrentScreenState? {
        while (true) {
            println("Вы в ${screenState?.nameOf}. Введите команду (цифру) из списка:")

            for (i in 0 until listOfCommands.size) {
                println("$i. ${listOfCommands[i]}")
            }

            if (scan.hasNextInt()) {
                val userInput = scan.nextInt()
                if(userInput < 0){
                    println("Вы ввели отрицательное число")
                }
                else if(userInput !in 0 until listOfCommands.size){
                    println("Такой команды (цифры) нет на экране")
                }

                else{
                    when(userInput) {
                        0 -> return CurrentScreenState.ArchiveCreation
                        listOfCommands.size - 1 -> return null // выход обратно
                        else -> checkArchiveNumber(userInput)
                    }
                }//в "элсе" выше проверка команды существующей - 1, 2, ... откроют архив под этим номером

            }//if проверки числа кончается

            else {
                println("Вы ввели нечисловое значение. Введите цифру, обозначающую команду")
                scan.next()
            }

        }
    }
    private fun checkArchiveNumber(userInput: Int): Note? {
        return Note("Заметка", "Какой-то контент внутри")
    }

}


class Archive(val archiveName: String, val notesList: MutableList<Note>)

class Note(val noteName: String, val noteData: String)



interface Selections {
    fun someSharedFunction()
}
