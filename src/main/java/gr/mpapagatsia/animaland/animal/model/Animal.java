package gr.mpapagatsia.animaland.animal.model;


import gr.mpapagatsia.animaland.trick.Trick;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animal")
public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String uuid;

    @Column(nullable = false)
    private String species;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "animal_tricks", joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "trick_id"))
    private List<Trick> tricks = new ArrayList<>();

    public void addTrick(Trick trick) {
        tricks.add(trick);
        trick.getAnimals().add(this);
    }
}
