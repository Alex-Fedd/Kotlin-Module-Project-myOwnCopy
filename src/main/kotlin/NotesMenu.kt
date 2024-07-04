import java.util.Scanner

class NotesMenu(
    private val referenceToMainMenu: MainMenu,
    private var refToCurrentArchive: Archive
) {

    private val scanner = Scanner(System.`in`)
    private val menuCommandsList = mutableListOf(
        MenuCommand("Создать заметку", { createANote() }, null),
        MenuCommand("Вернуться", { leave() }, null)
    )

    private fun leave() {
        referenceToMainMenu.startArchivesMenu()
    }

    private fun menuChecker() {
        val input = scanner.nextLine()
        referenceToMainMenu.menuCheck(input = input, menuCommandsList)

    }

    private fun createANote() {
        println("Задайте название новой заметке")
        val userInput = scanner.nextLine()

        if (userInput != "" && userInput != null && userInput.isNotBlank()) {
            var contentInputForNote: String
            while (true) {
                println("Введите свой текст для заметки")
                contentInputForNote = scanner.nextLine()
                if (contentInputForNote == null || contentInputForNote == "" || contentInputForNote.isBlank()) {
                    println("Заметка не может быть пустой")
                } else {
                    break
                }
            }

            val myNote = Note(userInput, contentInputForNote)
            refToCurrentArchive.notesList.add(myNote)

            menuCommandsList.add(MenuCommand("Заметка $userInput", {
                openTheNote(
                    userInput,
                    contentInputForNote
                )
            }, refToCurrentArchive))
            println("Заметка $userInput создана")

        } else {
            println("Заметка должна иметь название (пустое значение не принимается)")
        }
    }


    private fun openTheNote(noteNamePassed: String, noteContentPassed: String) {
        val isNoteInCurrentArchive =
            refToCurrentArchive.notesList.any { it.noteName == noteNamePassed && it.noteData == noteContentPassed }
        val noteIndex =
            refToCurrentArchive.notesList.indexOfFirst { it.noteName == noteNamePassed && it.noteData == noteContentPassed }

        if (isNoteInCurrentArchive && noteIndex >= 0) {
            println("Заметка $noteNamePassed открыта")
            println("Содержание: $noteContentPassed")
        } else {
            println("Заметка не найдена в текущем архиве.")
        }
    }


    fun printNotesMenu(archive: Archive) {
        selectCurrentArchive(archive)
        while (true) {
            println("Выберите заметку или создайте новую, выбрав нужную команду.")
            for (i in 0 until menuCommandsList.size) {// было 0..1 - поменял на антил сайз
                println("${i + 1}. ${menuCommandsList[i].command}")
            }

            menuChecker()
        }
    }

    private fun selectCurrentArchive(archive: Archive) {
        refToCurrentArchive = archive
        menuCommandsList.clear()
        menuCommandsList.add(MenuCommand("Создать заметку", { createANote() }, null))
        menuCommandsList.add(MenuCommand("Вернуться", { leave() }, null))
        for (note in archive.notesList) {
            menuCommandsList.add(MenuCommand("Заметка ${note.noteName}", {
                openTheNote(
                    note.noteName,
                    note.noteData
                )
            }, archive))
        }
    }

}