package com.my_todo_list.my_todo_list.repository;

import com.my_todo_list.my_todo_list.model.Task;
import com.my_todo_list.my_todo_list.model.Team;
import com.my_todo_list.my_todo_list.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByTitle(String title);
    Set<Task> findByAssignees(User user);
    Set<Task> findByTeams(Team team);

}