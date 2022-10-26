create table user_tasks
(
    task_id SERIAL PRIMARY KEY,
    employee_id INTEGER not null,
    task_text text not null,
    task_status text not null,
    task_created_at timestamp not null,
    task_deleted_at timestamp,
    task_completed_at timestamp
);

create table employees
(
    employee_id SERIAL PRIMARY KEY,
    employee_name text not null,
    is_admin boolean not null,
    employee_created_at timestamp,
    employee_deleted_at timestamp
);