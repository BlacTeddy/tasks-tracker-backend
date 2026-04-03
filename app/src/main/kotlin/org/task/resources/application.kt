import io.ktor.server.routing.*
import repository.TaskRepository
import routes.taskRoutes

fun Application.module() {

    val repo = TaskRepository()

    routing {
        taskRoutes(repo)
    }
}
