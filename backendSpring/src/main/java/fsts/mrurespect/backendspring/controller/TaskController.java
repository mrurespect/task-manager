package fsts.mrurespect.backendspring.controller;

import fsts.mrurespect.backendspring.entity.Task;
import fsts.mrurespect.backendspring.service.TaskService;
import org.springframework.http.ResponseEntity;
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
    @PutMapping("/task")
    public Task updateTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }
    @DeleteMapping("/task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

}
