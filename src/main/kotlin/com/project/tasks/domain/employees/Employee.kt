package com.project.tasks.domain.employees

import com.project.tasks.api.commands.CreateEmployeeCommand
import java.time.LocalDateTime

data class Employee(
    var employeeId: Int,
    var name: String,
    var isAdmin: Boolean,
    var createdAt: LocalDateTime,
    var deletedAt: LocalDateTime? = null,
){
    companion object {
        fun fromCommand(command: CreateEmployeeCommand): Employee {
            return Employee(
                employeeId = Math.random().toInt(),
                name=command.employeeName,
                isAdmin = command.isAdmin,
                createdAt = LocalDateTime.now()
            )
        }
    }
}