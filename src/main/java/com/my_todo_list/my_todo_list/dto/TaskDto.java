package com.my_todo_list.my_todo_list.dto;

import com.my_todo_list.my_todo_list.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Long id;
    private String title;
    private String text;
    private TaskStatus status;
    private LocalDateTime task_create_data;
    private LocalDateTime task_deadline;

}
