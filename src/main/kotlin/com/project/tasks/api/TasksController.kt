package com.project.tasks.api

import com.project.tasks.api.commands.CreateTaskCommand
import com.project.tasks.api.commands.UpdateTaskTextCommand
import com.project.tasks.domain.tasks.Task
import com.project.tasks.usecases.tasks.*
import org.springframework.beans.factory.annotation.Autowired
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
    private val createTaskUseCase: CreateTaskUseCase,
    private val getTaskUseCase: GetTaskUseCase,
    private val getAllTasksForUserUseCase: GetAllTasksForUserUseCase,
    private val updateTaskTextUseCase: UpdateTaskTextUseCase,
    private val completeTaskUseCase: CompleteTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) {

    @GetMapping("/employee/{employeeId}")
    fun getTasks(@PathVariable employeeId: Int) = getAllTasksForUserUseCase.getTask(employeeId)

    @GetMapping("/{taskId}")
    fun getTask(@PathVariable taskId: Int) = getTaskUseCase.getTask(taskId)

    @PostMapping
    fun createTask(@RequestBody createTaskCommand: CreateTaskCommand) = createTaskUseCase.createTask(createTaskCommand)

    @PutMapping("/{taskId}/update-text")
    fun updateTaskText(@PathVariable taskId: Int, @RequestBody updateTaskTextCommand: UpdateTaskTextCommand) =
        updateTaskTextUseCase.updateTaskText(taskId, updateTaskTextCommand.updatedTaskText)

    @PutMapping("/{taskId}/complete")
    fun completeTask(@PathVariable taskId: Int) =
        completeTaskUseCase.completeTask(taskId)

    @DeleteMapping("/{taskId}")
    fun deleteTask(@PathVariable taskId: Int) =
        deleteTaskUseCase.deleteTask(taskId)
}