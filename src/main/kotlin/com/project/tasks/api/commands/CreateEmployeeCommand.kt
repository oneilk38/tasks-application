package com.project.tasks.api.commands

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateEmployeeCommand(
    @JsonProperty("employee_name")
    val employeeName: String ,
    @JsonProperty("is_admin")
    val isAdmin: Boolean
)
