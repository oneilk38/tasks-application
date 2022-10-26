package com.project.tasks.api

import com.project.tasks.api.commands.CreateEmployeeCommand
import com.project.tasks.usecases.EmployeeUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employees")
class EmployeesController(
    private val employeeUseCase: EmployeeUseCase
){
    @GetMapping
    fun getEmployees() = employeeUseCase.findEmployees()

    @GetMapping("/{employeeId}")
    fun getEmployee(@PathVariable employeeId: Int) = employeeUseCase.findEmployee(employeeId)

    @PostMapping
    fun createEmployee(@RequestBody createEmployeeCommand: CreateEmployeeCommand) =
        employeeUseCase.createEmployee(createEmployeeCommand)

    @DeleteMapping("/{employeeId}")
    fun deleteEmployee(@PathVariable employeeId: Int) = employeeUseCase.deleteEmployee(employeeId)
}