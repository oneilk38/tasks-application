package com.project.tasks.infra.postgres

import com.project.tasks.domain.tasks.Task
import com.project.tasks.domain.tasks.TaskRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import org.jooq.generated.tables.UserTasks.USER_TASKS
import org.jooq.generated.tables.records.UserTasksRecord

@Repository
class PostgresTasksRespository(
    private val dslContext: DSLContext
) : TaskRepository {
    override fun createTask(task: Task): Task {
        val record = dslContext.insertInto(USER_TASKS).columns(
            USER_TASKS.EMPLOYEE_ID,
            USER_TASKS.TASK_TEXT,
            USER_TASKS.TASK_STATUS,
            USER_TASKS.TASK_CREATED_AT,
        ).values(
            task.employeeId,
            task.taskText,
            task.status,
            task.createdAt,
        ).returningResult(USER_TASKS.TASK_ID).fetchOne();

        if (record != null) {
            task.id = record.getValue(USER_TASKS.TASK_ID)
        }

        return task;
    }

    override fun findTask(taskId: Int): Task? {
        val record = dslContext.selectFrom(USER_TASKS).where(USER_TASKS.TASK_ID.eq(taskId)).fetchOne() ?: return null

        return record.toTask()
    }

    private fun UserTasksRecord.toTask(): Task = Task(
        id = this.taskId,
        employeeId = this.employeeId,
        taskText = this.taskText,
        status = this.taskStatus,
        createdAt = this.taskCreatedAt,
        completedAt = this.taskCompletedAt,
        deletedAt = this.taskDeletedAt
    )
}