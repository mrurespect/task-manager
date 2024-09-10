package fsts.mrurespect.backendspring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data @AllArgsConstructor @ToString
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id ;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password ;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks ;

    public User() {
        if (tasks==null)tasks=new ArrayList<>();
    }
    public void addTask(Task task){
        tasks.add(task);
    }
    public void deleteTask(Task task){
        tasks.remove(task);
    }

}
