package com.project.tasks.domain.tasks;

interface TaskRepository {
    fun createTask(task: Task) : Task
    fun findTask(taskId: Int): Task?
    fun findAllTasksForEmployee(employeeId: Int, taskStatus: String? = null): Collection<Task>
    fun deleteTask(taskId: Int)
    fun completeTask(taskId: Int)
    fun updateTaskText(taskId: Int, updatedText: String)
}
