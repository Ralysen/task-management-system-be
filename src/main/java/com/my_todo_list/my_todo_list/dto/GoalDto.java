package com.my_todo_list.my_todo_list.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoalDto {

    private Long id;
    private String title;
    private String describe;

}
