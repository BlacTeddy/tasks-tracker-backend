package com.example.tasks.routes

import data.CreateTaskRequest
import data.UpdateTaskRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import repository.TaskRepository
import io.ktor.http.*

fun Route.taskRoutes(repo: TaskRepository) {

    // GET /tasks
    get("/tasks") {
        call.respond(repo.getAllTasks())
    }

    // GET /tasks/{id}
    get("/tasks/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid ID")

        val task = repo.getTaskById(id)
            ?: return@get call.respond(HttpStatusCode.NotFound, "Task not found")

        call.respond(task)
    }

    // POST /tasks
    post("/tasks") {
        val request = call.receive<CreateTaskRequest>()
        val newTask = repo.addTask(request.text)
        call.respond(HttpStatusCode.Created, newTask)
    }

    // PUT /tasks/{id}
    put("/tasks/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: return@put call.respond(HttpStatusCode.BadRequest, "Invalid ID")

        val request = call.receive<UpdateTaskRequest>()
        val updated = repo.updateTask(id, request.text, request.done)
            ?: return@put call.respond(HttpStatusCode.NotFound, "Task not found")

        call.respond(updated)
    }

    // DELETE /tasks/{id}
    delete("/tasks/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: return@delete call.respond(HttpStatusCode.BadRequest, "Invalid ID")

        val success = repo.deleteTask(id)
        if (!success) {
            return@delete call.respond(HttpStatusCode.NotFound, "Task not found")
        }

        call.respond(HttpStatusCode.NoContent)
    }
}
