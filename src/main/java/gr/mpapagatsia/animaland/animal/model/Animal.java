package gr.mpapagatsia.animaland.animal.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String uuid;

    private String species;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tricks_id", nullable = true)
    private List<Trick> tricks;
}
