package com.my_todo_list.my_todo_list.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {

    private Long id;
    private String name;
    private String describes;
    private Set<UserDto> teamMembers;

}
