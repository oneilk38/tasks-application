package com.project.tasks.usecases.tasks

import com.project.tasks.domain.tasks.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CompleteTaskUseCase (
    private val taskRepository: TaskRepository
) {
    fun completeTask(taskId: Int) = taskRepository.completeTask(taskId, LocalDateTime.now())
}