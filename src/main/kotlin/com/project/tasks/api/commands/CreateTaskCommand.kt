package com.project.tasks.api.commands

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateTaskCommand(
    @JsonProperty("employee_id")
    val employeeId: Int,
    val text: String
)
