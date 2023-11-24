package com.my_todo_list.my_todo_list.repository;

import com.my_todo_list.my_todo_list.model.Team;
import com.my_todo_list.my_todo_list.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Override
    void deleteById(Long aLong);
}