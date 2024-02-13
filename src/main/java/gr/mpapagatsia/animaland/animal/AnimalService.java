package gr.mpapagatsia.animaland.animal;

import gr.mpapagatsia.animaland.animal.dto.AnimalDto;
import gr.mpapagatsia.animaland.animal.model.Animal;
import gr.mpapagatsia.animaland.trick.TrickDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnimalService {

    Page<AnimalDto> getAllAnimals(Pageable page);

    Animal createAnimal(AnimalDto animalDto);

    TrickDto doTrick(String id);

    List<TrickDto> learnTrick(String id);
}
