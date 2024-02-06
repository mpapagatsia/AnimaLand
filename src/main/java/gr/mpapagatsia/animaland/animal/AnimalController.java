package gr.mpapagatsia.animaland.animal;

import gr.mpapagatsia.animaland.animal.dto.AnimalDto;
import gr.mpapagatsia.animaland.animal.dto.PagedDto;
import gr.mpapagatsia.animaland.animal.dto.TrickDto;
import gr.mpapagatsia.animaland.animal.model.Animal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<PagedDto> getAll(Pageable page) {
        log.info("Fetching the list of available animals.");
        return ResponseEntity.ok(PagedDto.fromPage(animalService.getAllAnimals(page)));
    }

    @PostMapping(name = "Create Animal", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Animal> create(@RequestBody AnimalDto animalDto) {
        log.info("Create animal: {} ", animalDto);
        var saved = animalService.createAnimal(animalDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}/doTrick", name = "Animal does a trick")
    public ResponseEntity<TrickDto> doTrick(@PathVariable String id) {
        log.info("Request animal with id {} to do a trick.", id);

        return ResponseEntity.ok(animalService.doTrick(id));
    }

    @PutMapping(value = "{id}/learnTrick", name = "Animal learns a new trick", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrickDto>> learnTrick(@PathVariable String id) {
        log.info("Animal with id {} is about to learn a new trick.", id);

        return ResponseEntity.ok(animalService.learnTrick(id));
    }

}
