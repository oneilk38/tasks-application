package com.project.tasks.infra.postgres

import com.project.tasks.domain.employees.Employee
import com.project.tasks.domain.employees.EmployeeRepository
import org.jooq.DSLContext
import org.jooq.generated.tables.records.EmployeesRecord
import org.jooq.generated.tables.Employees.EMPLOYEES
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class PostgresEmployeesRepository(
    private val dslContext: DSLContext
) : EmployeeRepository  {
    override fun createEmployee(employee: Employee): Employee {
        val record = dslContext.insertInto(EMPLOYEES).columns(
            EMPLOYEES.EMPLOYEE_NAME,
            EMPLOYEES.IS_ADMIN,
            EMPLOYEES.EMPLOYEE_CREATED_AT
        ).values(
            employee.name,
            employee.isAdmin,
            employee.createdAt
        ).returningResult(EMPLOYEES.EMPLOYEE_ID).fetchOne()

        if(record != null){
            employee.employeeId = record.getValue(EMPLOYEES.EMPLOYEE_ID)
        }

        return employee
    }

    override fun findEmployee(employeeId: Int): Employee? {
        val record = dslContext.selectFrom(EMPLOYEES)
            .where(EMPLOYEES.EMPLOYEE_ID.eq(employeeId))
            .fetchOne() ?: return null

        return record.toEmployee()
    }


    override fun findEmployees(): Collection<Employee> {
        val records = dslContext.selectFrom(EMPLOYEES).fetch()

        return records.map { it.toEmployee() }
    }

    override fun deleteEmployee(employeeId: Int, deletedAt: LocalDateTime) {
        dslContext.update(EMPLOYEES)
            .set(EMPLOYEES.EMPLOYEE_DELETED_AT, deletedAt)
            .where(EMPLOYEES.EMPLOYEE_ID.eq(employeeId))
            .execute()
    }

    private fun EmployeesRecord.toEmployee() = Employee(
        employeeId = this.employeeId,
        name = this.employeeName,
        isAdmin = this.isAdmin,
        createdAt = this.employeeCreatedAt,
        deletedAt = this.employeeDeletedAt
    )
}