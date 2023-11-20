package com.my_todo_list.my_todo_list.repository;

import com.my_todo_list.my_todo_list.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    Goal findByTitle(String title);
}