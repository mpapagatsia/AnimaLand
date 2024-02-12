package gr.mpapagatsia.animaland.animal;

import gr.mpapagatsia.animaland.animal.dto.AnimalDto;
import gr.mpapagatsia.animaland.trick.TrickDto;
import gr.mpapagatsia.animaland.animal.model.Animal;
import gr.mpapagatsia.animaland.trick.Trick;
import gr.mpapagatsia.animaland.exception.AnimalNotFoundException;
import gr.mpapagatsia.animaland.exception.TrickNotFoundException;
import gr.mpapagatsia.animaland.trick.TrickRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
        Animal animal = AnimalDto.toEntity(animalDto);

        //Add known tricks and create the new ones
        List<Trick> tricksToSave = new ArrayList<>();
        if (!CollectionUtils.isEmpty(animalDto.tricks())) {
            animalDto.tricks().forEach(trickName -> {
                Optional<Trick> trick = trickRepository.findByName(trickName);
                trick.ifPresentOrElse(tricksToSave::add, ()-> tricksToSave.add(Trick.builder().species(animalDto.species()).name(trickName).build()));
            });
            animal.setTricks(tricksToSave);
        }

        return repository.save(animal);
    }

    public TrickDto doTrick(@NotNull String uuid) {
        var animal = repository.findByUuid(uuid).orElseThrow(() -> new AnimalNotFoundException(uuid));

        if (CollectionUtils.isEmpty(animal.getTricks())) {
            throw new TrickNotFoundException(uuid);
        }

        Random random = new Random();
        return new TrickDto(animal.getTricks().get(random.nextInt(animal.getTricks().size())).getName());
    }

    @Transactional
    public List<TrickDto> learnTrick(String uuid) {
        var animal = repository.findByUuid(uuid).orElseThrow(() -> new AnimalNotFoundException(uuid));

        var speciesTricks = trickRepository.getAllBySpecies(animal.getSpecies());
        if(CollectionUtils.isEmpty(speciesTricks)) {
            throw new TrickNotFoundException(uuid);
        }
        var trick = getRandomTrick(speciesTricks, animal);

        trick.getAnimals().add(animal);
        animal.getTricks().add(trick);

        return animal.getTricks().stream().map(t -> new TrickDto(t.getName())).toList();
    }

    /**
     * @param speciesTricks
     * @param animal
     * @return a new trick of the available for the Animal
     */
    private static Trick getRandomTrick(List<Trick> speciesTricks, Animal animal) {
        Random random = new Random();
        var trickOptions = speciesTricks.stream().filter(t -> !animal.getTricks().contains(t)).toList();
        if(CollectionUtils.isEmpty(trickOptions)) {
            throw new TrickNotFoundException(animal.getName(), "already knows all tricks!");
        }
        return trickOptions.get(random.nextInt(trickOptions.size()));
    }

}
