package gr.mpapagatsia.animaland.animal;

import gr.mpapagatsia.animaland.animal.dto.AnimalDto;
import gr.mpapagatsia.animaland.animal.dto.TrickDto;
import gr.mpapagatsia.animaland.animal.model.Animal;
import gr.mpapagatsia.animaland.animal.model.Trick;
import gr.mpapagatsia.animaland.trick.TrickRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class AnimalService {
    private final AnimalRepository repository;

    private final TrickRepository trickRepository;

    @Autowired
    public AnimalService(AnimalRepository repository, TrickRepository trickRepository) {
        this.repository = repository;
        this.trickRepository = trickRepository;
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

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Transactional
    public List<TrickDto> learnTrick(String uuid) throws Exception {
        var animal = repository.findByUuid(uuid).orElseThrow(() -> new Exception("Animal not found"));

        List<Trick> speciesTricks = trickRepository.getAllBySpecies(animal.getSpecies());
        if(speciesTricks.isEmpty()) {
            return Collections.emptyList();
        }
        var trick = getRandomTrick(speciesTricks, animal);
        if(trick.isEmpty()){
            return Collections.emptyList();
        }
        trick.get().getAnimals().add(animal);
        animal.getTricks().add(trick.get());

        return animal.getTricks().stream().map(t -> new TrickDto(t.getName())).toList();
    }

    private static Optional<Trick> getRandomTrick(List<Trick> speciesTricks, Animal animal) {
        Random random = new Random();
        var trickOptions = speciesTricks.stream().filter(t -> !animal.getTricks().contains(t)).toList();
        if(trickOptions.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(trickOptions.get(random.nextInt(trickOptions.size())));
    }

}
