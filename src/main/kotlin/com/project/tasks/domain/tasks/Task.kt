package com.project.tasks.domain.tasks;

import com.project.tasks.api.commands.CreateTaskCommand
import java.time.LocalDateTime

data class Task(
    var id: Int,
    var employeeId: Int,
    var taskText: String,
    var status: String,
    var createdAt: LocalDateTime,
    var deletedAt: LocalDateTime? = null,
    var completedAt: LocalDateTime? = null
){
    companion object {
        fun fromCommand(command: CreateTaskCommand): Task {
            return Task(
                id=Math.random().toInt(),
                employeeId = command.employeeId,
                taskText=command.taskText,
                status = "CREATED",
                createdAt = LocalDateTime.now()
            )
        }
    }
}
