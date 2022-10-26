package com.project.tasks.domain.tasks;

import java.time.LocalDateTime

interface TaskRepository {
    fun createTask(task: Task) : Task
    fun findTask(taskId: Int): Task?
    fun findAllTasksForEmployee(employeeId: Int, taskStatus: String? = null): Collection<Task>
    fun deleteTask(taskId: Int, deletedAt: LocalDateTime)
    fun completeTask(taskId: Int, completedAt: LocalDateTime)
    fun updateTaskText(taskId: Int, updatedText: String)
}
