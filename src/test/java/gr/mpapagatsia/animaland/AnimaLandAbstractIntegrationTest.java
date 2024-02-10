package gr.mpapagatsia.animaland;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.mpapagatsia.animaland.animal.AnimalService;
import gr.mpapagatsia.animaland.animal.dto.AnimalDto;
import gr.mpapagatsia.animaland.animal.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AnimaLandAbstractIntegrationTest {

    @Autowired
    AnimalService animalService;

    ObjectMapper mapper = new ObjectMapper();

    protected Animal createCatBlue() throws JsonProcessingException {
        return animalService.createAnimal(mapper.readValue("""
                {
                    "name": "Blue",
                    "id": "id10",
                    "species": "cat",
                    "tricks": [
                      "walksOnLaptop"
                    ]
                  }
                  """, AnimalDto.class));
    }

    protected Animal createDogRomeo() throws JsonProcessingException {

        return animalService.createAnimal(mapper.readValue("""
                {
                  "name": "Romeo",
                  "id": "id11",
                  "species": "dog",
                  "tricks": [
                    "jumps",
                    "rollsOver",
                    "barks"
                  ]
                }
                """, AnimalDto.class));
    }

    protected Animal createDogJenny() throws JsonProcessingException {

        return animalService.createAnimal(mapper.readValue("""
                {
                   "name": "Jenny",
                   "id": "id3",
                   "species": "dog",
                   "tricks": [
                     "jumps",
                     "barks"
                   ]
                }
                 """, AnimalDto.class));
    }
}
