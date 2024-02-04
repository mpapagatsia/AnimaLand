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
            Random random = new Random();
            //TODO learn one that does not already know
            Trick trick = speciesTricks.get(random.nextInt(speciesTricks.size()));
            trick.getAnimals().add(animal);
            trickRepository.save(trick);

        return repository.findById(animal.getId()).get().getTricks().stream().map(t -> new TrickDto(t.getName())).toList();
    }

}
