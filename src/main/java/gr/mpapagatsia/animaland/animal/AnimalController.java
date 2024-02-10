package gr.mpapagatsia.animaland.animal;

import gr.mpapagatsia.animaland.animal.dto.AnimalDto;
import gr.mpapagatsia.animaland.animal.dto.PagedDto;
import gr.mpapagatsia.animaland.trick.TrickDto;
import gr.mpapagatsia.animaland.animal.model.Animal;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Get All", notes = "Returns a paginated list of all animals.")
    @GetMapping(name = "Get All Animals")
    public ResponseEntity<PagedDto> getAll(Pageable page) {
        log.info("Fetching the list of available animals.");
        return ResponseEntity.ok(PagedDto.fromPage(animalService.getAllAnimals(page)));
    }

    @ApiOperation(value = "Create", notes = "Creates a new animal.")
    @PostMapping(name = "Create Animal", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Animal> create(@RequestBody AnimalDto animalDto) {
        log.info("Create animal: {} ", animalDto);
        var saved = animalService.createAnimal(animalDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Do Trick", notes = "Request an animal to perform a trick.")
    @GetMapping(value = "{id}/doTrick", name = "Animal does a trick")
    public ResponseEntity<TrickDto> doTrick(@PathVariable String id) {
        log.info("Request animal with id {} to do a trick.", id);

        return ResponseEntity.ok(animalService.doTrick(id));
    }

    @ApiOperation(value = "Learn Trick", notes = "Request an animal to learn a new trick from the available tricks to its species, if any.")
    @PutMapping(value = "{id}/learnTrick", name = "Animal learns a new trick", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrickDto>> learnTrick(@PathVariable String id) {
        log.info("Animal with id {} is about to learn a new trick.", id);

        return ResponseEntity.ok(animalService.learnTrick(id));
    }

}
