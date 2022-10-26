package com.project.tasks.usecases

import com.project.tasks.api.commands.CreateTaskCommand
import com.project.tasks.domain.tasks.Task
import com.project.tasks.domain.tasks.TaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TasksUseCase (
    private val taskRepository: TaskRepository
) {

    @Transactional
    fun createTask(createTaskCommand: CreateTaskCommand): Task {
        val task = Task.fromCommand(createTaskCommand)

        return taskRepository.createTask(task)
    }

    fun deleteTask(taskId: Int) = taskRepository.deleteTask(taskId, LocalDateTime.now())

    fun getTasks(employeeId: Int, status: String? = null) = taskRepository.findAllTasksForEmployee(employeeId, status)

    fun getTask(taskId: Int) = taskRepository.findTask(taskId)

    fun completeTask(taskId: Int) = taskRepository.completeTask(taskId, LocalDateTime.now())

    fun updateTaskText(taskId: Int, updatedTaskText: String) = taskRepository.updateTaskText(taskId, updatedTaskText)
}