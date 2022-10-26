package com.project.tasks.domain.employees

import java.time.LocalDateTime

interface EmployeeRepository {
    fun createEmployee(employee: Employee): Employee
    fun findEmployee(employeeId: Int): Employee?
    fun findEmployees(): Collection<Employee>
    fun deleteEmployee(employeeId: Int, deletedAt: LocalDateTime)
}