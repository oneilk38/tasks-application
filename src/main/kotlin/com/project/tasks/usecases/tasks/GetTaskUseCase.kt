package com.project.tasks.usecases.tasks

import com.project.tasks.domain.tasks.TaskRepository
import org.springframework.stereotype.Service

@Service
class GetTaskUseCase(
    private val taskRepository: TaskRepository
) {
    fun getTask(taskId: Int) = taskRepository.findTask(taskId)
}