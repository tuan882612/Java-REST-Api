package api.rest.Controller.POST;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerPostTest {

    @Autowired
    MockMvc mockMvc;

    String baseUrl = "/api/v1/person/";

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
