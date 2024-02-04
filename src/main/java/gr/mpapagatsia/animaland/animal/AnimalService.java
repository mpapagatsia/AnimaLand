package gr.mpapagatsia.animaland.animal;

import gr.mpapagatsia.animaland.animal.dto.AnimalDto;
import gr.mpapagatsia.animaland.animal.dto.TrickDto;
import gr.mpapagatsia.animaland.animal.model.Animal;
import gr.mpapagatsia.animaland.animal.model.Trick;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class AnimalService {
    private final AnimalRepository repository;

    @Autowired
    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public Page<AnimalDto> getAllAnimals(Pageable page) {
        return repository.findAll(page).map(AnimalDto::fromEntity);
    }

    @Transactional
    public Animal createAnimal(AnimalDto animalDto) {
        return repository.save(AnimalDto.toEntity(animalDto));
    }

    public TrickDto doTrick(@NotNull String uuid) throws Exception{
        Optional<Animal> animal = repository.findByUuid(uuid);

        //TODO exception handling
        var tricks = animal.map(Animal::getTricks).orElseThrow(() -> new Exception("Animal not found"));
        if (!tricks.isEmpty()) {
            Random random = new Random();
            return new TrickDto(tricks.get(random.nextInt(tricks.size())).getName());
        }

        return TrickDto.emptyTrick();
    }
}
