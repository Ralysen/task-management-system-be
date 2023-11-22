package com.my_todo_list.my_todo_list.repository;

import com.my_todo_list.my_todo_list.model.Task;
import com.my_todo_list.my_todo_list.model.Team;
import com.my_todo_list.my_todo_list.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTasks_Id(Long id);

    User findByEmail(String email);
    User findByName(String name);
    Set<User> findByTask(Task task);
    Set<User> findByTeam(Team team);

}