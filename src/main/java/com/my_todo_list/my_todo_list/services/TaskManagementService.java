package com.my_todo_list.my_todo_list.services;

import com.my_todo_list.my_todo_list.dto.TaskDto;
import com.my_todo_list.my_todo_list.dto.TeamDto;
import com.my_todo_list.my_todo_list.dto.UserDto;
import com.my_todo_list.my_todo_list.enums.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
public interface TaskManagementService {

    //User operations
    void createUser(UserDto userDto);
    UserDto getUserById(Long id);
    Set<TeamDto> getTeamsByIdUser(Long id);
    Set<TaskDto> getTasksByIdUser(Long id);
    void AssignUserToTask(Long userId, Long taskId);
    void RemoveUserFromTask(Long userId, Long taskId);

    //Team operations
    void createTeam(TeamDto teamDto);
    void deleteTeam(Long teamId);
    TeamDto getTeamById(Long teamId);
    Set<UserDto> getMembersByTeamId(Long id);
    void addUserToTeam(Long teamId, Long userIds);
    void removeUserFromTeam(Long teamId, Long userIds);
    //Set<TaskDto> getTasksByTeamIdAndAssignee(Long teamId, Long assigneeId);

    //Task operations
    void createTask(TaskDto taskDto);
    TaskDto getTaskById(Long taskId);
    Set<TaskDto> getTasksByTeamId(Long teamId);
    void assignTaskToTeam(Long taskId, Long teamId);
    void changeTaskStatus(Long taskId, TaskStatus newStatus);
}
