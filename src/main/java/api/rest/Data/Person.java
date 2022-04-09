package api.rest.Data;

import javax.persistence.*;

@Entity
@Table
public class Person {

    @Id
    @SequenceGenerator(
            name = "p_sequence",
            sequenceName = "p_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "p_sequence"
    )
    private Long id;
    private String name;
    private String type;
    private String year;

    public Person(Long id,
                  String name,
                  String type,
                  String year) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.year = year;
    }

    public Person(String name,
                  String type,
                  String year) {
        this.name = name;
        this.type = type;
        this.year = year;
    }

    public Person() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", year=" + year;
    }
}
