package gr.mpapagatsia.animaland.animal;

import gr.mpapagatsia.animaland.animal.dto.AnimalDto;
import gr.mpapagatsia.animaland.animal.model.Animal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("animals")
@Slf4j
public class AnimalController {
    private final AnimalService animalService;

    @Autowired
    public AnimalController (AnimalService animalService) {
        this.animalService = animalService;
    }

    //TODO document
    @GetMapping
    public ResponseEntity<List<AnimalDto>> getAll() {
        log.info("Fetching the list of available animals");
        return new ResponseEntity<>(animalService.getAllAnimals(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Animal> create(@RequestBody AnimalDto animalDto) {
        log.info("Saving animalDto: " + animalDto);
        var saved = animalService.createAnimal(animalDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

}
