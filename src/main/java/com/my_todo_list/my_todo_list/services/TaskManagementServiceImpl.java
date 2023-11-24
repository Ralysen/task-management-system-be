package com.my_todo_list.my_todo_list.services;

import com.my_todo_list.my_todo_list.dto.TaskDto;
import com.my_todo_list.my_todo_list.dto.TeamDto;
import com.my_todo_list.my_todo_list.dto.UserDto;
import com.my_todo_list.my_todo_list.enums.TaskStatus;
import com.my_todo_list.my_todo_list.model.Task;
import com.my_todo_list.my_todo_list.model.Team;
import com.my_todo_list.my_todo_list.model.User;
import com.my_todo_list.my_todo_list.repository.TaskRepository;
import com.my_todo_list.my_todo_list.repository.TeamRepository;
import com.my_todo_list.my_todo_list.repository.UserRepository;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskManagementServiceImpl implements TaskManagementService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TeamRepository teamRepository;


    public TaskManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //User operations
    @Override
    public void createUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new Error("User already exists");
        }
        else {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        //Remember to set password security ******!!!!!
        user.setPassword(user.getPassword());
        userRepository.save(user);
        }
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
        return mapToUserDto(user);
    }

    @Override
    public Set<TeamDto> getTeamsByIdUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Set<Team> teams = user.getTeams();
        return teams.stream().map(this::mapToTeamDto).collect(Collectors.toSet());
    }

    @Override
    public Set<TaskDto> getTasksByIdUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Set<Task> assigneedTasks = user.getTasks();
        return assigneedTasks.stream().map(this::mapToTaskDto).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void AssignUserToTask(Long userId, Long taskId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        task.getAssignees().add(user);
        Set<User> userSet = task.getAssignees();
        taskRepository.assignUserToTask(userSet, taskId);
    }

    @Override
    @Transactional
    public void RemoveUserFromTask(Long userId, Long taskId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        task.getAssignees().remove(user);
        Set<User> userSet = task.getAssignees();
        taskRepository.removeUserFromTask(userSet, taskId);
    }

    //Team operations
    public void createTeam(TeamDto teamDto) {
        Team team = new Team();
        team.setName(teamDto.getName());
        team.setDescribes(teamDto.getDescribes());
        team.setTeamMembers(teamDto.getTeamMembers());
        team.setTasks(teamDto.getTasks());
        teamRepository.save(team);
    }

    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }

    public TeamDto getTeamById(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        TeamDto teamDto = mapToTeamDto(team);
        return teamDto;
    }

    @Override
    public Set<UserDto> getMembersByTeamId(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        Set<User> userSet = team.getTeamMembers();
        return userSet.stream().map(this::mapToUserDto).collect(Collectors.toSet());
    }

    @Override
    public void addUserToTeam(Long teamId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        team.getTeamMembers().add(user);
        teamRepository.save(team);
    }

    @Override
    public void removeUserFromTeam(Long teamId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        team.getTeamMembers().remove(user);
        teamRepository.save(team);
    }

    //Task operations
    public void createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTaskName(taskDto.getTaskName());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setTask_create_data(taskDto.getTask_create_data());
        task.setTask_deadline(taskDto.getTask_deadline());
        task.setAssignees(taskDto.getAssignees());
        task.setTeams(taskDto.getTeams());
        taskRepository.save(task);
    }

    public TaskDto getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        return mapToTaskDto(task);
    }

    public Set<TaskDto> getTasksByTeamId(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        Set<Task> taskSet = team.getTasks();
        return taskSet.stream().map(this::mapToTaskDto).collect(Collectors.toSet());
    }

    @Override
    public void assignTaskToTeam(Long teamId, Long taskId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        team.getTasks().add(task);
        teamRepository.save(team);
    }

    public void changeTaskStatus(Long taskId, TaskStatus taskStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        task.setStatus(taskStatus);
        taskRepository.save(task);
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private TeamDto mapToTeamDto(Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setName(team.getName());
        teamDto.setDescribes(team.getDescribes());
        teamDto.setTeamMembers(team.getTeamMembers());
        return teamDto;
    }

    private TaskDto mapToTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskName(task.getTaskName());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus());
        taskDto.setTask_create_data(task.getTask_create_data());
        taskDto.setTask_deadline(task.getTask_deadline());
        taskDto.setTeams(task.getTeams());
        taskDto.setAssignees(task.getAssignees());
        return taskDto;
    }
}
