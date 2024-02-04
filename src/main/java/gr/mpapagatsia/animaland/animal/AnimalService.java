package gr.mpapagatsia.animaland.animal;

import gr.mpapagatsia.animaland.animal.dto.AnimalDto;
import gr.mpapagatsia.animaland.animal.model.Animal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AnimalService {
    private final AnimalRepository repository;

    @Autowired
    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public List<AnimalDto> getAllAnimals() {
        return repository.findAll().stream().map(AnimalDto::fromEntity).toList();
    }

    @Transactional
    public Animal createAnimal(AnimalDto animalDto) {
        return repository.save(AnimalDto.toEntity(animalDto));
    }
}
