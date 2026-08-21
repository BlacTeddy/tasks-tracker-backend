# Tasks Tracker: From CLI Script to Ktor REST API

A progressive Kotlin project documenting my journey from a procedural command-line script to a modular, object-oriented backend service, culminating in an asynchronous **Ktor REST API** with JSON file persistence.

---

## Architectural Evolution

This repository captures a deliberate progression in software design and systems architecture:

### 1. Procedural CLI Script & Flat-File Storage
* **Foundations:** Started as a single-file script using standard loops (`while (true)`), mutable lists, and Java's `File` API to read and write pipe-delimited records (`tasks.csv`).
* **Input Safety:** Implemented robust numeric parsing (`toIntOrNull()`) and string trimming to handle user actions safely.

### 2. Object-Oriented Refactor (`TaskManager`)
* **Encapsulation:** Refactored global state into a dedicated `TaskManager` class, protecting internal mutable lists from external pollution.
* **Automatic Initialization:** Utilized Kotlin's `init` block to automatically load saved state on startup.
* **Safe State Mutation:** Designed mutation methods (`addTask`, `editTask`, `toggleDone`, `deleteTask`) to automatically synchronize disk storage upon every state change.

### 3. Ktor Backend & Repository Pattern
* **API Architecture:** Upgraded the application into a web server using **Ktor**, routing HTTP verbs (`GET`, `POST`, `PUT`, `DELETE`) cleanly through a dedicated `TaskRepository`.
* **Structured JSON Serialization:** Replaced flat-file parsing with `kotlinx.serialization` to manage structured JSON (`tasks.json`) storage with incremental ID tracking (`nextId`).

---

## Tech Stack
* **Language:** Kotlin
* **Backend Framework:** Ktor (Server)
* **Serialization:** Kotlinx Serialization (`json`)
* **Persistence:** Local JSON Storage (`tasks.json`) via Java File API
* **Build Tool:** Gradle (Kotlin DSL)
* **Runtime:** JVM

---

## Project Structure
```text
tasks-tracker-backend/
│
├── app/src/main/kotlin/org/task/
│   ├── routes/
│   │   └── taskRoutes.kt    # Ktor REST endpoint definitions (GET, POST, PUT, DELETE)
│   ├── repository/
│   │   └── TaskRepository.kt# Encapsulated state management, ID tracking, and JSON persistence
│   ├── data/
│   │   └── Task.kt          # Data models and request request/response structures
│   └── Application.kt       # Server configuration and plugin setup
├── app/src/main/resources/
│   └── tasks.json           # Persistent local data store (auto-generated)
├── build.gradle.kts         # Gradle build configuration
└── README.md                # Project documentation
