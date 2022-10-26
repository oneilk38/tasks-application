package com.project.tasks.api

import com.project.tasks.infra.auth.AuthService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.slf4j.Logger

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val logger: Logger
) {
    @PostMapping("/login/{employeeId}")
    fun login(@PathVariable employeeId: Int): String {
        val sessionToken = authService.login(employeeId)

        logger.info("Successfully logged in employee [${employeeId}] with session token [${sessionToken}]")

        return sessionToken
    }
}