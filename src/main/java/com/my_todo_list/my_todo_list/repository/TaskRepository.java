package com.my_todo_list.my_todo_list.repository;

import com.my_todo_list.my_todo_list.enums.TaskStatus;
import com.my_todo_list.my_todo_list.model.Task;
import com.my_todo_list.my_todo_list.model.Team;
import com.my_todo_list.my_todo_list.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.assignees = :userSet WHERE t.id = :taskId")
    void removeUserFromTask(@Param("userSet") Set<User> userSet, @Param("taskId") Long taskId);

    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.assignees = :userSet WHERE t.id = :taskId")
    void assignUserToTask(@Param("userSet") Set<User> userSet, @Param("taskId") Long taskId);
}