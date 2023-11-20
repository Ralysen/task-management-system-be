package com.my_todo_list.my_todo_list.repository;

import com.my_todo_list.my_todo_list.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByTitle(String title);
}