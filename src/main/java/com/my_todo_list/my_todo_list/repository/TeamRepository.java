package com.my_todo_list.my_todo_list.repository;

import com.my_todo_list.my_todo_list.model.Team;
import com.my_todo_list.my_todo_list.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findTeamByName(String teamName);
    Set<Team> findByMember(User user);
}