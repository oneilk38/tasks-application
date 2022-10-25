package com.project.tasks.usecases.tasks

import com.project.tasks.domain.tasks.TaskRepository
import org.springframework.stereotype.Service

@Service
class UpdateTaskTextUseCase(
    private val taskRepository: TaskRepository
) {
    fun updateTaskText(taskId: Int, updatedTaskText: String) = taskRepository.updateTaskText(taskId, updatedTaskText)
}