# Kotlin Task Manager

A modularized backend/storage layer built in **Kotlin**, designed to manage task state, perform CRUD operations, and handle local file persistence safely. This project represents an architectural refactor of a procedural CLI script into an encapsulated, object-oriented class (`TaskManager`).

---

## Architectural Evolution & Key Concepts
* **Encapsulation:** Isolated the internal mutable task list (`mutableListOf<Task>`) and file-saving logic inside a dedicated `TaskManager` class, protecting state from global pollution.
* **Automatic Initialization (`init`):** Leveraged Kotlin’s `init` block to automatically trigger data loading from local storage the moment the manager class is instantiated.
* **Safe Index Validation:** Replaced basic error-prone bounds checking with Kotlin’s idiomatic range check (`index in tasks.indices`) to safely handle updates, completions, and deletions.
* **Automatic Persistence:** Designed mutation methods (`addTask`, `editTask`, `toggleDone`, `deleteTask`) to automatically invoke `saveTasks()` upon state changes, keeping the flat file synchronized.

---

## Tech Stack
* **Language:** Kotlin
* **Persistence:** Java Flat-File API (`java.io.File`) using pipe-delimited storage (`.csv/.txt`)
* **Design Pattern:** Separation of concerns (State management isolated from CLI/UI execution)

---

## Project Structure
```text
tasks-tracker/
│
├── Task.kt          # Data class defining the task model (text, done status)
├── TaskManager.kt   # Encapsulated state management, validation, and file I/O logic
├── Main.kt          # CLI loop, user input routing, and execution interface
├── tasks.csv        # Persistent local data store (auto-generated)
└── README.md        # Project documentation
