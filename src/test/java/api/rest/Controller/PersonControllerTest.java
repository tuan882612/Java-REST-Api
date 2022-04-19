package api.rest.Controller;

import api.rest.Data.Person;
import api.rest.Service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

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
    public void invalidGetInput() throws Exception {
        this.mockMvc.perform(get(baseUrl+"w"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Add new person to the Database")
    public void addPerson() throws Exception {
        RequestBuilder tryPost = MockMvcRequestBuilders
                .post(baseUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test\",\"type\":1111,\"year\":1111}")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(tryPost)
                .andDo(print())
                .andExpect(status().isCreated());

        this.mockMvc.perform(get(baseUrl+"1111"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Return FOUND if type or name is already in the DataBase")
    public void alreadyExist() throws Exception{
        RequestBuilder tryPost = MockMvcRequestBuilders
                .post(baseUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Tuan\",\"type\":1111,\"year\":1111}")
                .contentType(MediaType.APPLICATION_JSON);

        RequestBuilder tryPost1 = MockMvcRequestBuilders
                .post(baseUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test\",\"type\":1001,\"year\":1111}")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(tryPost)
                .andDo(print())
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(tryPost1)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Return BAD REQUEST if less than three variables are in the JSON body")
    public void invalidBody() throws Exception {
        RequestBuilder tryPost2 = MockMvcRequestBuilders
                .post(baseUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Tuan\",\"type\":1111}")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(tryPost2)
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Return BAD REQUEST if any variable is NULL in Json body")
    public void invalidBody1() throws Exception {

        RequestBuilder tryPost3 = MockMvcRequestBuilders
                .post(baseUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Tuan\",\"type\":1111,\"year\":}")
                .contentType(MediaType.APPLICATION_JSON);

        RequestBuilder tryPost4 = MockMvcRequestBuilders
                .post(baseUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Tuan\",\"type\":,\"year\":1111}")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(tryPost3)
                .andDo(print())
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(tryPost4)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}