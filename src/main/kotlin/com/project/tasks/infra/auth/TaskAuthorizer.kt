package com.project.tasks.infra.auth

import com.project.tasks.domain.employees.EmployeeRepository
import com.project.tasks.domain.tasks.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskAuthorizer(
    private val employeeRepository: EmployeeRepository,
    private val taskRepository: TaskRepository
) {
    fun isAdmin(employeeId: Int) : Boolean {
        val employee = employeeRepository.findEmployee(employeeId) ?: return false

        return employee.isAdmin
    }

    fun canView(currentUserId: Int, requestedTaskId: Int) : Boolean {
        val task = taskRepository.findTask(requestedTaskId) ?: return false

        return isAdmin(currentUserId)
    }

    fun canAdd(currentUserId: Int, requestedEmployeeId: Int) : Boolean {
        return currentUserId == requestedEmployeeId
    }

    fun canModify(currentUserId: Int, taskId: Int) : Boolean {
        val task = taskRepository.findTask(taskId) ?: return false

        return task.employeeId == currentUserId || isAdmin(currentUserId)
    }
}