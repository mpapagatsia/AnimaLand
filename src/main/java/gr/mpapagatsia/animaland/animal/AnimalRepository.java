package gr.mpapagatsia.animaland.animal;

import gr.mpapagatsia.animaland.animal.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
