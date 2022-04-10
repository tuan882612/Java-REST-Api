package api.rest.Controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    String baseUrl = "/api/v1/person/";

    @Test
    @DisplayName("Creates default entities and returns all entities")
    public void getPersons() throws Exception {
        this.mockMvc.perform(get(baseUrl))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Return specific entity")
    public void getPerson() throws Exception {
        this.mockMvc.perform(get(baseUrl+"1001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Return NOT FOUND if the id does not exist")
    public void doesNotExist() throws Exception {
        this.mockMvc.perform(get(baseUrl+"0000"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Return BAD REQUEST if the id does not match format")
    public void invalidRequest() throws Exception {
        this.mockMvc.perform(get(baseUrl+"w"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}