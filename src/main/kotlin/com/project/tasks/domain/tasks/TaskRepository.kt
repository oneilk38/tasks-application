package com.project.tasks.domain.tasks;

interface TaskRepository {
    fun createTask(task: Task) : Task
    fun findTask(taskId: Int): Task?
    fun findAllTasksForEmployee(employeeId: Int): Collection<Task>
    fun deleteTask(taskId: Int)
    fun updateTask(taskId: Int, updatedText: String)
}
