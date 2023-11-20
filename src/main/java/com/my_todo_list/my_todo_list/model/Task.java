package com.my_todo_list.my_todo_list.model;

import com.my_todo_list.my_todo_list.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String text;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(nullable = false)
    private LocalDateTime task_create_data;

    private LocalDateTime task_deadline;

    @ManyToMany(mappedBy = "tasks")
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;
}
