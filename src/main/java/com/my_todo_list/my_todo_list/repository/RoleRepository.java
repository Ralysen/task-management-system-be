package com.my_todo_list.my_todo_list.repository;

import com.my_todo_list.my_todo_list.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}