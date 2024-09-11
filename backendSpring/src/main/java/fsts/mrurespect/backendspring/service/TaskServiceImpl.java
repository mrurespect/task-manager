package fsts.mrurespect.backendspring.service;

import fsts.mrurespect.backendspring.entity.Task;
import fsts.mrurespect.backendspring.entity.User;
import fsts.mrurespect.backendspring.repository.TaskRepository;
import fsts.mrurespect.backendspring.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

@Override
public List<Task> getTasks() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
        String username = ((UserDetails)principal).getUsername();
        User authUser = userRepository.getUserByEmail(username);
        return taskRepository.findTasksByUser(authUser);
    }
    // in case the user is not authenticated
    throw new RuntimeException("User not authenticated");
}

    @Override
    public Task saveTask(Task task) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            User authUser = userRepository.getUserByEmail(username);
            task.setUser(authUser);
            authUser. addTask(task);
            userRepository.save(authUser);
        }else {
            throw new RuntimeException("User not authenticated");
        }
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task=new Task();
        task.setId(id);
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            User authUser = userRepository.getUserByEmail(username);

            authUser.deleteTask(task);
            userRepository.save(authUser);
        }else {
            throw new RuntimeException("User not authenticated");
        }
         taskRepository.delete(task);
    }
}
