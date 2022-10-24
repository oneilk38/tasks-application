package com.project.tasks

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TasksController {

    @GetMapping("/tasks")
    fun getTasks(): String {
        return "TODO: GET TASKS: LATEST! - 000000"
    }
}