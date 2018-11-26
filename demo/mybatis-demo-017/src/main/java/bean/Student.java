package bean;

import lombok.Data;

import java.util.List;

@Data
public class Student {

    private Long id;

    private String name;

    private List<Teacher> teacherList;
}
