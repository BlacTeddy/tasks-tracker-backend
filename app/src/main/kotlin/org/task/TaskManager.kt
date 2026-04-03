/*COMPILE:
    kotlinc Main.kt -include-runtime -d main.jar
RUN:
    java -jar main.jar
*/
import java.io.File

class TaskManager(private val file: File = File("tasks.csv")) {

    private val tasks = mutableListOf<Task>()

    init {
        loadTasks()
    }

    private fun saveTasks() {
        file.printWriter().use { out ->
            tasks.forEach { task ->
                out.println("${task.text.replaceFirstChar{it.uppercase()}}|${task.done}")
            }
        }
    }

    private fun loadTasks() {
        if (!file.exists()) return

        file.forEachLine { line ->
            val parts = line.split("|")
            if (parts.size == 2) {
                val text = parts[0]
                val done = parts[1].toBoolean()
                tasks.add(Task(text, done))
            }
        }
    }
}

    fun addTask(text: String) {
        tasks.add(Task(text))
        saveTasks()
    }

    fun editTask(index: Int, newText: String) {
        if (index in tasks.indices) {
            tasks[index].text = newText
            saveTasks() //does save re-write the whole list when called//which file gets the data class from Task.kt
        } else {
            println("Invalid task number")
        }
    }

    fun toggleDone(index: Int) {
        if (index in tasks.indices) {
            tasks[index].done = !tasks[index].done
            saveTasks()
        } else {
            println("Invalid task number")
        }
    }

    fun deleteTask(index: Int) {
        if (index in tasks.indices) {
            tasks.removeAt(index)
            saveTasks()
        } else {
            println("Invalid task number")
        }
    }

    fun viewTasks() {
        if (tasks.isEmpty()) {
            println("No tasks yet.")
            return
        }
        tasks.forEachIndexed { index, task ->
            val mark = if (task.done) "[x]" else "[ ]"
            println("${index + 1}. ${task.text} $mark")
        }
    }

// fun confirm(index: Int): Boolean{
//     print("Are you sure you want to delete ${index+1}. ${tasks[index].text} [Y/N]")
//     val ans = readln().trim().lowercase()
//     if(ans=="y" || ans=="yes"){
//         return true
//     }else{return false}
// }
