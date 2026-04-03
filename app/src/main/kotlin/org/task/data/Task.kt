package com.example.tasks.data

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: int,
    val text: String, 
    var done: Boolean
    
)

