package gr.mpapagatsia.animaland.animal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trick")
public class Trick implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String species;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tricks")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private List<Animal> animals;
}
