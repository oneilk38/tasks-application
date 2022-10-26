package com.project.tasks.api.commands

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateTaskTextCommand (
    @JsonProperty("task_text")
    val updatedTaskText: String
)
