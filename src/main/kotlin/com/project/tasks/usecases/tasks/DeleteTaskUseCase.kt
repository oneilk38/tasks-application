package com.project.tasks.usecases.tasks

import com.project.tasks.domain.tasks.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DeleteTaskUseCase(
    private val taskRepository: TaskRepository
) {
    fun deleteTask(taskId: Int) = taskRepository.deleteTask(taskId, LocalDateTime.now())
}