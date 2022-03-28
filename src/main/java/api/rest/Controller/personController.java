package api.rest.Controller;

import api.rest.DataLayer.person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/person")
public class personController {

    @GetMapping
    public List<person> getStudent() {
        return List.of(
                new person(
                        1L,
                        "Tuan",
                        "CSE",
                        2024,
                        19
                )
        );
    }
}
