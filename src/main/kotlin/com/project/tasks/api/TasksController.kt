package com.project.tasks.api

import com.project.tasks.api.commands.CreateTaskCommand
import com.project.tasks.domain.tasks.Task
import com.project.tasks.usecases.tasks.CreateTaskUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TasksController (
    private val createTaskUseCase: CreateTaskUseCase
) {

    @GetMapping
    fun getTasks(): String {
        return "TODO: GET TASKS: LATEST!"
    }

    @PostMapping
    fun createTask(@RequestBody createTaskCommand: CreateTaskCommand): Task {
        return createTaskUseCase.createTask(createTaskCommand)
    }
}