package com.project.tasks.infra.auth

import org.springframework.stereotype.Component

@Component
class RedisCache: AuthCache {
    override fun authenticate(employeeSessionToken: String) =
        (employeeSessionToken == "employee-session-token-1" || employeeSessionToken == "employee-session-token-2")

    override fun login(employeeId: Int): String {
        return "TODO"
    }

    override fun getEmployeeIdFromSessionToken(employeeSessionToken: String): Int {
        if(employeeSessionToken == "employee-session-token-1"){
            return 1
        }
        if(employeeSessionToken == "employee-session-token-2"){
            return 2
        }
        return 3
    }
}