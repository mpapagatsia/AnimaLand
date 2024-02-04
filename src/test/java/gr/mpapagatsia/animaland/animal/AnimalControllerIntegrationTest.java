package gr.mpapagatsia.animaland.animal;

import gr.mpapagatsia.animaland.AnimaLandAbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    void whenAnimalControllerInjected_thenNotNull() {
        assertNotNull(controller);
    }

    @Test
    void whenAnimalControllerGetAll_ThenCorrectResponse() throws Exception {
        mockMvc.perform(request(HttpMethod.GET, API_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['pageable']['paged']").value(true))
                .andExpect(jsonPath("$['pageable']['paged']").value(true));
    }
}