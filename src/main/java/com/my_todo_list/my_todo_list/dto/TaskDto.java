package com.my_todo_list.my_todo_list.dto;

import com.my_todo_list.my_todo_list.enums.TaskStatus;
import com.my_todo_list.my_todo_list.model.Team;
import com.my_todo_list.my_todo_list.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Long id;
    private String taskName;
    private String description;
    private TaskStatus status;
    private LocalDateTime task_create_data;
    private LocalDateTime task_deadline;
    private Set<Team> teams;
    private Set<User> assignees;
}
