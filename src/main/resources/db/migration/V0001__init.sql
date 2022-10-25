create table user_tasks
(
    id SERIAL PRIMARY KEY,
    employee_id INTEGER not null,
    text text not null
);