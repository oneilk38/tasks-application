package com.project.tasks.infra.auth

import com.project.tasks.domain.employees.EmployeeRepository
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class EmployeeAuthorizer(
    private val employeeRepository: EmployeeRepository,
    private val logger: Logger
) {
    fun isAdmin(employeeId: Int) : Boolean {
        val employee = employeeRepository.findEmployee(employeeId) ?: return false
        logger.info("Employee [$employeeId], admin: ${employee.isAdmin}, tostring: $employee")
        return employee.isAdmin
    }

    fun canViewEmployee(currentUserId: Int, requestedEmployeeId: Int) : Boolean {
        logger.info("Current employee ID $currentUserId, requested: $requestedEmployeeId")
        if(currentUserId == requestedEmployeeId){
            return true
        }

        return isAdmin(currentUserId)
    }

    fun canViewEmployees(currentUserId: Int) : Boolean {
        return isAdmin(currentUserId)
    }

    fun canAddEmployee(currentUserId: Int) : Boolean {
        return isAdmin(currentUserId)
    }

    fun canDeleteDeleteEmployee(currentUserId: Int) : Boolean {
        return isAdmin(currentUserId)
    }
}