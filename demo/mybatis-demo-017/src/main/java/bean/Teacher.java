package bean;

import lombok.Data;

import java.util.List;

@Data
public class Teacher {

    private Long id;

    private String name;

    private List<Student> studentList;

}
