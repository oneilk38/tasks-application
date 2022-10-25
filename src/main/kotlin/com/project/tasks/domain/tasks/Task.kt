package com.project.tasks.domain.tasks;

import com.project.tasks.api.commands.CreateTaskCommand

data class Task(
    private var id: Int,
    val employeeId: Int,
    val text: String
){
    companion object {
        fun fromCommand(command: CreateTaskCommand): Task {
            return Task(
                id=Math.random().toInt(),
                employeeId = command.employeeId,
                text=command.text
            )
        }
    }

    fun getId() = id

    fun setId(newId: Int) {
        id = newId
    }
}
