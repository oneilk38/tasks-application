package com.project.tasks.api

import com.project.tasks.api.commands.CreateTaskCommand
import com.project.tasks.api.commands.UpdateTaskTextCommand
import com.project.tasks.usecases.TasksUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TasksController (
    private val taskUseCase: TasksUseCase,
) {

    @GetMapping("/employee/{employeeId}")
    fun getTasks(@PathVariable employeeId: Int) = taskUseCase.getTask(employeeId)

    @GetMapping("/{taskId}")
    fun getTask(@PathVariable taskId: Int) = taskUseCase.getTask(taskId)

    @PostMapping
    fun createTask(@RequestBody createTaskCommand: CreateTaskCommand) = taskUseCase.createTask(createTaskCommand)

    @PutMapping("/{taskId}/update-text")
    fun updateTaskText(@PathVariable taskId: Int, @RequestBody updateTaskTextCommand: UpdateTaskTextCommand) =
        taskUseCase.updateTaskText(taskId, updateTaskTextCommand.updatedTaskText)

    @PutMapping("/{taskId}/complete")
    fun completeTask(@PathVariable taskId: Int) =
        taskUseCase.completeTask(taskId)

    @DeleteMapping("/{taskId}")
    fun deleteTask(@PathVariable taskId: Int) =
        taskUseCase.deleteTask(taskId)
}