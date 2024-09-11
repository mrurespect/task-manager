package fsts.mrurespect.backendspring.service;

import fsts.mrurespect.backendspring.entity.Task;
import fsts.mrurespect.backendspring.entity.User;

import java.util.List;

public interface TaskService {
    List<Task> getTasks();
    Task saveTask(Task task);
    Task updateTask(Task task);
    void deleteTask(int id);
}
