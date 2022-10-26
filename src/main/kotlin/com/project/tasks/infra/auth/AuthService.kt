package com.project.tasks.infra.auth

import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authCache: AuthCache,
){
    fun login(employeeId: Int) = authCache.login(employeeId)

    fun authenticate(employeeSessionToken: String) =
        authCache.authenticate(employeeSessionToken)

    fun getEmployeeIdFromSessionToken(employeeSessionToken: String) =
        authCache.getEmployeeIdFromSessionToken(employeeSessionToken)
}