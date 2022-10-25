package com.project.tasks.usecases.tasks

import com.project.tasks.domain.tasks.TaskRepository
import org.springframework.stereotype.Service

@Service
class GetAllTasksForUserUseCase(
    private val taskRepository: TaskRepository
) {
    fun getTask(employeeId: Int, status: String? = null) = taskRepository.findAllTasksForEmployee(employeeId, status)
}