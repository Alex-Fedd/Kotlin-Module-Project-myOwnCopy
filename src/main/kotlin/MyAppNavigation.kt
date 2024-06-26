import java.util.Scanner

open class MyAppNavigation : AppMenuSelection {
    val scanner = Scanner(System.`in`)
    fun getCurrentState(screenState: String): CurrentScreenState?{
        return when(screenState){
            CurrentScreenState.ArchiveSelection.nameOf.lowercase() -> {
                CurrentScreenState.ArchiveSelection
            }
            else -> null
        }
    }

    override fun showMenu() {
    }
}


// общий код, где запускаю и навигируюсь и функции запускаю
// в зависимости от состояния. Наследую интерфейс и от состояний запусккаю переходы

    //if state=ArchiveSelScreen -> archiveObj.funThatDoesArchStuff()
    //if state=NoteSelScreen -> noteSelObj.funWithNoteMenuStuff()
    //if st=ArCreateScreen -> arCreatScreenStuff()
    //if st=NoteCreateScreen -> NoteCreateScreenstuff()
    //if st=NoteScreen -> add or modify Note stuff()
    //^логику от состояния вызываю. Отчасти общая. Надо как-то передавать верно объекты и функции каждого экрана чтоб их реализацию вызвать
