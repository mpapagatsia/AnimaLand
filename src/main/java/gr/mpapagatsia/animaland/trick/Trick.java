package gr.mpapagatsia.animaland.trick;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gr.mpapagatsia.animaland.animal.model.Animal;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    private List<Animal> animals = new ArrayList<>();

}
