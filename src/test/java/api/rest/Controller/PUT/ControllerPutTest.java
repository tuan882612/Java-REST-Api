package api.rest.Controller.PUT;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerPutTest {

    @Autowired
    MockMvc mockMvc;

    String baseUrl = "/api/v1/person/";

    @Test
    @DisplayName("Updates entity is okay")
    public void updatePerson() throws Exception {
        RequestBuilder tryPut = MockMvcRequestBuilders
                .put(baseUrl+"1001")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test\",\"type\":1001,\"year\":1111}")
                .contentType(MediaType.APPLICATION_JSON);

        RequestBuilder tryGet = MockMvcRequestBuilders
                .get(baseUrl+"1001")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test\",\"type\":1001,\"year\":1111}")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(tryPut)
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(tryGet)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Return NOT FOUND if type not in database")
    public void updatePerson1() throws Exception {
        RequestBuilder tryPut = MockMvcRequestBuilders
                .put(baseUrl+"1001")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test\",\"type\":1000,\"year\":1111}")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(tryPut)
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Return BAD REQUEST if type input does not path variable")
    public void updatePerson2() throws Exception {
        RequestBuilder tryPut = MockMvcRequestBuilders
                .put(baseUrl+"1001")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test\",\"type\":1002,\"year\":1111}")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(tryPut)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
