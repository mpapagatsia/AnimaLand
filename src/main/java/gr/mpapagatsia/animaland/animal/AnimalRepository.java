package gr.mpapagatsia.animaland.animal;

import gr.mpapagatsia.animaland.animal.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Optional<Animal> findByUuid(String uuid);
}
