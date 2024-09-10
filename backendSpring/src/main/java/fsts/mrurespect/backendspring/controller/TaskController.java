package fsts.mrurespect.backendspring.controller;

import fsts.mrurespect.backendspring.entity.Task;
import fsts.mrurespect.backendspring.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return taskService.getTasks();
    }
    @PostMapping("/task")
    public Task addTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }
    @DeleteMapping("/task")
    public String deleteTask(@RequestBody Task task){
        taskService.deleteTask(task);
        return "task deleted successfully";
    }
}
