import java.util.Scanner

//внизу класс с общей логикой - пытаюсь безуспешно из "Архивов" перенести сюда общее, пока просто скопировал и не понимаю
class MyAppNavigation {

    private val scan = Scanner(System.`in`)
    var screenState: CurrentScreenState? = CurrentScreenState.ArchiveSelection//меню 1-e текущее (выбор архивов = 1й экран)
    private val listOfCommands = mutableListOf("Создать архив","Выйти")



    fun startAppMenu(): CurrentScreenState? {
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
        return Note("Заметка", "Что-то")
    }

    /*fun start(){ // мб так? Пока тут не понимаю, как далее верно продолжить
            while(true){
                when (screenState){
                    CurrentScreenState.ArchiveSelection -> {
                        val archives = Archives()
                        archives.showArchivesMenu()
                    }
                    CurrentScreenState.ArchiveCreation -> Archive().archiveMenu()
                    CurrentScreenState.NoteSelection -> Archive().archiveMenu()
                    CurrentScreenState.NoteCreation -> Archive().archiveMenu()
                    CurrentScreenState.NoteScreen -> Archive().archiveMenu()
                    null -> return
                }
            */

}






/*open class MyAppNavigation : AppMenuSelection {
    val scanner = Scanner(System.`in`)
    fun getCurrentState(screenState: String): CurrentScreenState?{
        return when(screenState){
            CurrentScreenState.ArchiveSelection.nameOf.lowercase() -> {
                CurrentScreenState.ArchiveSelection
            }
            else -> null
        }
    }



}*/


// общий код, где запускаю и навигируюсь и функции запускаю
// в зависимости от состояния. Наследую интерфейс и от состояний запусккаю переходы

    //if state=ArchiveSelScreen -> archiveObj.funThatDoesArchStuff()
    //if state=NoteSelScreen -> noteSelObj.funWithNoteMenuStuff()
    //if st=ArCreateScreen -> arCreatScreenStuff()
    //if st=NoteCreateScreen -> NoteCreateScreenstuff()
    //if st=NoteScreen -> add or modify Note stuff()
    //^логику от состояния вызываю. Отчасти общая. Надо как-то передавать верно объекты и функции каждого экрана чтоб их реализацию вызвать
