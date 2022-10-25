package com.project.tasks.usecases.tasks;

import com.project.tasks.api.commands.CreateTaskCommand
import com.project.tasks.domain.tasks.Task;
import com.project.tasks.domain.tasks.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class CreateTaskUseCase (
    private val taskRepository: TaskRepository
) {
    @Transactional
    fun createTask(createTaskCommand: CreateTaskCommand): Task {
        val task = Task.fromCommand(createTaskCommand)

        return taskRepository.createTask(task)
    }
}
