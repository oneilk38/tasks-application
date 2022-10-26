package com.project.tasks.infra.auth

interface AuthCache {
    fun login(employeeId: Int) : String
    fun authenticate(employeeSessionToken: String) : Boolean
    fun getEmployeeIdFromSessionToken(employeeSessionToken: String) : Int
}