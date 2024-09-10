package fsts.mrurespect.backendspring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "task")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user ;
}
