package com.project.tasks.usecases

import com.project.tasks.api.commands.CreateEmployeeCommand
import com.project.tasks.domain.employees.Employee
import com.project.tasks.domain.employees.EmployeeRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class EmployeeUseCase(private val employeeRepository: EmployeeRepository) {

    fun createEmployee(createEmployeeCommand: CreateEmployeeCommand): Employee {
        val employee = Employee.fromCommand(createEmployeeCommand)

        return employeeRepository.createEmployee(employee)
    }

    fun findEmployee(employeeId: Int): Employee? = employeeRepository.findEmployee(employeeId)
    fun findEmployees(): Collection<Employee> = employeeRepository.findEmployees()

    fun deleteEmployee(employeeId: Int) = employeeRepository.deleteEmployee(employeeId, LocalDateTime.now())
}