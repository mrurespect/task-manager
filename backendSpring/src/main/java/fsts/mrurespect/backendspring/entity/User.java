package fsts.mrurespect.backendspring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
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
    private List<Task> user ;

}
