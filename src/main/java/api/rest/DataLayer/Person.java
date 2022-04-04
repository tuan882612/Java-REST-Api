package api.rest.DataLayer;

public class Person {
    private Long id;
    private String name;
    private String type;
    private Integer year;
    private Integer age;

    public Person() {
    }

    public Person(Long id,
                  String name,
                  String type,
                  Integer year,
                  Integer age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.year = year;
        this.age = age;
    }

    public Person(String name,
                  String type,
                  Integer year,
                  Integer age) {
        this.name = name;
        this.type = type;
        this.year = year;
        this.age = age;
    }

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", year=" + year +
                ", age=" + age +
                '}';
    }
}