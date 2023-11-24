package com.my_todo_list.my_todo_list.repository;

import com.my_todo_list.my_todo_list.model.Task;
import com.my_todo_list.my_todo_list.model.Team;
import com.my_todo_list.my_todo_list.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByTasks_Id(Long id);
    User findByEmail(String email);
    User findByName(String name);
}