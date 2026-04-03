package com.example.tasks.data

import kotlinx.serialization.Serializable

@Serializable
data class UpdateTaskRequest(
    val text: String? = null,
    val done: Boolean? = null
)
