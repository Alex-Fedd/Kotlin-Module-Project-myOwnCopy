fun main() {
    println("Запускаем приложение \"Заметки\"")
    val startApp = MyAppNavigation()
    startApp.startAppMenu()
}






// data class MyArchive(val name: String)
// data class MyNote(val name: String)
// interface MenuSelection{
// fun getBack()
// fun input()
// fun createElement()
// }
//
//
//
//
// Задача - сделать прилку ЗАМЕТКИ
// добавлять архивы (это набор заметок)
// добавлять заметки сами в архивы (в набор)
// просматривать заметки, кот добавил
// в любой моментя юзер может выйти с экрана выбора архива, или с экрана выбора заметок, или с экрана
// заметки. И тогда попадает в предыдщуий список
//
// 5 экранов:
// выбор архива (набор Зм)
// создаю архив
// выбираю заметку
// создаю заметку
// сам экран заметки -- тут д.б.возможность вывести текст заметки
//
// 5 экранов можно на две группы условно разделить:
//  1 - собсна выбирать могу из списка элемент (заметка или архив наверн) и создавать объект
//  2 - меню выбора - тут выбор арх или зам + сам экран зам
//  пример выбора архива: список арх: 0-создать а, 1-мой созданный арх, 2-выход
//
// //Важно: выход из программы есть только один. Во всех остальных случаях мы возвращаемся
// //на предыдущий экран.
//
// //выбор пункта меню реагировать д.корректно: не цифра; если цифра но такого элемента нет - сказать..;
//
// //для каждого экрана своя логика
// //избежать повторов стоит. КАК?
// //через общий код - все экраны с выбором элементов в меню (интерф?)
// //экраны выбора имеют общую навигацию и ввод
// //этот кусок с выбором в меню элементов общо сделаем; класс отедльный; мб интер и енам для состояний?
// //действия считывания и перехода вперед, назад + создания и выхода там как функции? Интерф этот наследуется
// //наследуется экранами-лбъектами? Типа архив и заметка и так каждый экран реализит интер и умеет делать повторяющееся
// //возможно...
//
// //что в итоге должно быть:
// //-меню1. тут могу добавить и посмотреть архивы
// //-меню2. тут добавляю и смотрю заметки
// //-в заметке могу текст добавить; могу смотреть текст заметок
// //-БЕЗ повторений одного и  того же кода:
// //-логика считывания ввода юзера +  вывода пунктов на экран переиспользовать одно и то же должна!
//
// /*Подсказки
// Рекомендуем начать с самого первого меню — архивы. Затем переместить общую логику
// в отдельный класс и свериться с требованиями к заданию.
// Далее оставшиеся меню будет написать намного легче.
//
// Каждое меню советуем делать в отдельном файле, чтобы проще было ориентироваться.
//
// Для переиспользования общего кода рекомендуем использовать отдельный класс, который содержит код:
// По выводу пунктов меню;
// По чтению пользовательского ввода;
// По выполнению определённых операций на выбор пункта меню.
//
// Для всего этого советуем использовать изменяемый список, который содержит в себе
// название пункта и лямбду, которая вызовется при выборе этого пункта.
//
// Для ввода стоит использовать бесконечный цикл, который повторяется до тех пор, пока пользователь не выберет выход.
// Все данные должны храниться в памяти и удаляться после завершения программы.
//
// Само чтение из консоли можно реализовать через Scanner.
// Для этого добавьте в начало файла import java.util.Scanner и в месте кода,
// где хотите прочитать строчку из консоли, введите Scanner(System.`in`).nextLine().
//