package fsts.mrurespect.backendspring.repository;

import fsts.mrurespect.backendspring.entity.Task;
import fsts.mrurespect.backendspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findTasksByUser(User user);
}
