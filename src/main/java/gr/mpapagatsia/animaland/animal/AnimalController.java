package gr.mpapagatsia.animaland.animal;

import gr.mpapagatsia.animaland.animal.dto.AnimalDto;
import gr.mpapagatsia.animaland.animal.model.Animal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "animals", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AnimalController {
    private final AnimalService animalService;

    @Autowired
    public AnimalController (AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping(name = "Get All Animals")
    public ResponseEntity<Page<AnimalDto>> getAll(Pageable page) {
        log.info("Fetching the list of available animals");
        return new ResponseEntity<>(animalService.getAllAnimals(page), HttpStatus.OK);
    }

    @PostMapping(name = "Create Animal", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Animal> create(@RequestBody AnimalDto animalDto) {
        log.info("Saving animalDto: " + animalDto);
        var saved = animalService.createAnimal(animalDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

}
