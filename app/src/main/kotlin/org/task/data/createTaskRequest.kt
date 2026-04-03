package com.example.tasks.data

import kotlinx.serialization.Serializable

@Serializable
data class CreateTaskRequest(
    val text: String
)
