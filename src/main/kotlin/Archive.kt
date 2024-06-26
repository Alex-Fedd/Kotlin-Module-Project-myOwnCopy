import java.util.Scanner

class Archives {
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

class Archive(val archiveName: String, val notesList: ArrayList<Note>)



//пока тут по архиву пишу
//интересно - если уже создал 1-2-5 архивов - то надо их отобразить в меню строками.
// Это енамы и стейты? енамы ж константы по сути
//тогда мб надо проверять сколько имеюо объектов и циклом добавлять строки
// и туда вставлять переменные с именем объектов архивов?
