package com.project.tasks.domain.tasks;

import org.springframework.stereotype.Repository
import java.util.*

interface TaskRepository {
    fun createTask(task: Task) : Task
//    fun findTask(taskId: Int): Optional<Task>
//    fun findAllTasksForEmployee(employeeId: Int): Collection<Task>
//    fun deleteTask(taskId: Int)
//    fun updateTask(taskId: Int, updatedText: String)
}
