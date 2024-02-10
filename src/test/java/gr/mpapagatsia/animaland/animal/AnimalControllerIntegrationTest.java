package gr.mpapagatsia.animaland.animal;

import gr.mpapagatsia.animaland.AnimaLandAbstractIntegrationTest;
import gr.mpapagatsia.animaland.animal.model.Animal;
import gr.mpapagatsia.animaland.trick.Trick;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.in;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AnimalControllerIntegrationTest extends AnimaLandAbstractIntegrationTest {
    private static final String API_PATH = "/animals/";
    @Autowired
    private AnimalController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AnimalRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    void whenAnimalControllerInjected_thenNotNull() {
        assertNotNull(controller);
    }

    @Test
    void whenAnimalControllerGetAll_ThenCorrectResponse() throws Exception {
        //given
        List<Animal> animals = List.of(createCatBlue(), createDogRomeo());

        //when
        mockMvc.perform(request(HttpMethod.GET, API_PATH))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.animals").isNotEmpty())
                .andExpect(jsonPath("$.animals.length()").value(animals.size()))
                .andExpect(jsonPath("$.animals[0].name").value(equalTo("Blue")))
                .andExpect(jsonPath("$.animals[1].name").value(equalTo("Romeo")))
                .andExpect(jsonPath("$.pageIndex").isNotEmpty())
                .andExpect(jsonPath("$.pageSize").isNotEmpty())
                .andExpect(jsonPath("$.totalElements").value(equalTo(2)))
                .andExpect(jsonPath("$.totalPages").isNotEmpty());

    }

    @Test
    void whenAnimalControllerDoTrick_ThenCorrectResponse() throws Exception {
        //given
        Animal animal = createDogRomeo();

        //when
        mockMvc.perform(request(HttpMethod.GET, API_PATH + animal.getUuid() + "/doTrick"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trick", in(animal.getTricks().stream().map(Trick::getName).toList())));

    }

    @Test
    void whenAnimalControllerLearnTrick_ThenCorrectResponse() throws Exception {
        //given
        var animal = createDogRomeo();
        var animal2 = createDogJenny();

        //TODO write a good assertion
        Set<String> dogTricks = Stream.concat(animal.getTricks().stream().map(Trick::getName), animal2.getTricks().stream().map(Trick::getName)).collect(Collectors.toSet());

        //when
        mockMvc.perform(request(HttpMethod.PUT, API_PATH + animal2.getUuid() + "/learnTrick"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }
}