package gr.mpapagatsia.animaland.trick;

import gr.mpapagatsia.animaland.animal.model.Trick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrickRepository extends JpaRepository<Trick, Long> {
    List<Trick> getAllBySpecies(String species);
}
