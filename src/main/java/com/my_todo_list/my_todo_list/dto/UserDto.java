package com.my_todo_list.my_todo_list.dto;

import com.my_todo_list.my_todo_list.model.Task;
import com.my_todo_list.my_todo_list.model.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<Task> tasks;
    private Set<Team> teams;
}
