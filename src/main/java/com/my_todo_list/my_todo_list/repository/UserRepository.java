package com.my_todo_list.my_todo_list.repository;

import com.my_todo_list.my_todo_list.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}