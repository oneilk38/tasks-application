package com.project.tasks.infra.postgres

import com.project.tasks.domain.tasks.Task
import com.project.tasks.domain.tasks.TaskRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import org.jooq.generated.tables.UserTasks.USER_TASKS
import org.jooq.Record
import org.jooq.*

@Repository
class PostgresTasksRespository(
    private val dslContext: DSLContext
) : TaskRepository {
    override fun createTask(task: Task): Task {
        val record = dslContext.insertInto(USER_TASKS).columns(
            USER_TASKS.EMPLOYEE_ID,
            USER_TASKS.TEXT
        ).values(
            task.employeeId,
            task.text
        ).returningResult(USER_TASKS.ID).fetchOne();

        if (record != null) {
            task.setId(record.getValue(USER_TASKS.ID))
        }

        return task;
    }
}