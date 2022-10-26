package com.project.tasks.api

import com.project.tasks.api.commands.CreateEmployeeCommand
import com.project.tasks.domain.employees.Employee
import com.project.tasks.infra.auth.AuthService
import com.project.tasks.infra.auth.EmployeeAuthorizer
import com.project.tasks.usecases.EmployeeUseCase
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employees")
class EmployeesController(
    private val employeeUseCase: EmployeeUseCase,
    private val authService: AuthService,
    private val employeeAuthorizer: EmployeeAuthorizer,
    private val logger: Logger
){

//    fun runAfterAuth(employeeId: Int, employeeSessionToken: String, action: String, funcToRun:() -> Any): ResponseEntity<Any> {
//        logger.info("Checking is Employee [$employeeId] with session token [$employeeSessionToken] authenticated...")
//
//        if(!authService.authenticate(employeeId, employeeSessionToken)){
//            logger.error("Employee $employeeId cannot $action as they are not authenticated!")
//            return ResponseEntity("Unauthenticated", HttpStatus.UNAUTHORIZED)
//        }
//
//        logger.info("Employee [$employeeId] is authenticated, checking can they perform action: [$action]...")
//
//        if(!authService.authenticate(employeeId, employeeSessionToken)){
//            logger.error("Employee $employeeId cannot $action as they are not authenticated!")
//            return ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED)
//        }
//
//        logger.info("Employee [$employeeId] can perform action: [$action]")
//
//        val response = funcToRun()
//
//        return ResponseEntity(response, HttpStatus.OK)
//    }

    fun runAfterAuthV2(authenticate: () -> Boolean, authorize: () -> Boolean, funcToRun:() -> Any?): ResponseEntity<Any> {
        if(!authenticate()){
            return ResponseEntity("Unauthenticated", HttpStatus.UNAUTHORIZED)
        }

        if(!authorize()){
            return ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED)
        }

        val response = funcToRun()

        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping
    fun getEmployees(
        @RequestHeader("current-user-session-token") employeeSessionToken: String
    ): ResponseEntity<Any> {
        return runAfterAuthV2(
            authenticate = { authService.authenticate(employeeSessionToken) },
            authorize = {
                employeeAuthorizer.canViewEmployees(
                    authService.getEmployeeIdFromSessionToken(employeeSessionToken)
                )
            },
            funcToRun = { employeeUseCase.findEmployees() }
        )
    }


    @GetMapping("/{employeeId}")
    fun getEmployee(
        @PathVariable employeeId: Int,
        @RequestHeader("current-user-session-token") employeeSessionToken: String
    ) : ResponseEntity<Any> {
        return runAfterAuthV2(
            authenticate = { authService.authenticate(employeeSessionToken) },
            authorize = {
                employeeAuthorizer.canViewEmployee(
                    currentUserId = authService.getEmployeeIdFromSessionToken(employeeSessionToken),
                    requestedEmployeeId = employeeId
                )
            },
            funcToRun = { employeeUseCase.findEmployee(employeeId) }
        )
    }

    @PostMapping
    fun createEmployee(@RequestBody createEmployeeCommand: CreateEmployeeCommand) =
        employeeUseCase.createEmployee(createEmployeeCommand)

    @DeleteMapping("/{employeeId}")
    fun deleteEmployee(@PathVariable employeeId: Int) = employeeUseCase.deleteEmployee(employeeId)
}