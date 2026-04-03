package com.example.tasks.repository

import data.Task
import data.TaskData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
/*
private val tasks: MutableList<Task>
private var nextId: Int
init { loadTasks() }
private fun loadTasks()  
private fun saveTasks()  
fun getAllTasks(): List<Task>  
fun getTaskById(id: Int): Task?  
fun addTask(text: String): Task
fun updateTask(id: Int, text: String?, done: Boolean?): Task?
*/

class TaskRepository {
    // In-memory storage database. All tasks live here while the server is running.
    private val tasks = mutableListOf<Task>() 
    private var nextId = 1  //This keeps track of the next unique ID to assign.

    // Runs automatically when the repository is created
    init {
        loadTasks()
    }

    // --- File + JSON handling ---
private fun loadTasks() {
    val file = File("src/main/resources/tasks.json")

    // If the file doesn't exist, create an empty one
    if (!file.exists()) {
        saveTasks()  // creates a default file with nextId=1 and empty list
        return
    }
    // Read the file content as text
    val jsonText = file.readText()

    // If the file is empty, treat it as no tasks
    if (jsonText.isBlank()) {
        saveTasks()
        return
    }
    // Decode JSON into TaskData
    val data = Json.decodeFromString<TaskData>(jsonText)

    // Fill in-memory storage
    tasks.clear()
    tasks.addAll(data.tasks)

    // Restore nextId
    nextId = data.nextId
}


private fun saveTasks() {
    val file = File("src/main/resources/tasks.json")

    // Wrap current in-memory data into TaskData
    val data = TaskData(
        nextId = nextId,
        tasks = tasks
    )

    // Convert Kotlin object → JSON string
    val jsonText = Json.encodeToString(data)

    // Write JSON to file (overwrites entire file)
    file.writeText(jsonText)
}


    // --- Read operations ---
    fun getAllTasks(): List<Task> {
        return tasks
    }

    fun getTaskById(id: Int): Task? {
        return tasks.find { it.id == id }
    }
    /*for(task in tasks){
        if(id == tasks.id){
            return task}
    }    return null*///how find() is doing

    // --- Write operations ---
    fun addTask(text: String): Task {
        val task = Task(
            id = nextId,
            text = text,
            done = false
        )

        tasks.add(task)
        nextId++
        saveTasks()

        return task
    }


    fun updateTask(id: Int, text: String?, done: Boolean?): Task? {
    val existing = getTaskById(id) ?: return null

    val updated = Task(
        id = existing.id,
        text = text ?: existing.text,
        done = done ?: existing.done
    )
    val index = tasks.indexOf(existing)
    tasks[index] = updated
    saveTasks()
    
    return updated
    }

    fun deleteTask(id: Int): Boolean {
    val existing = getTaskById(id) ?: return false

    tasks.remove(existing)
    saveTasks()

    return true
    }
}