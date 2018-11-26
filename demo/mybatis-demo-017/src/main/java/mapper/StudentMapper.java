package mapper;

import bean.Student;

public interface StudentMapper {

    // 根据 id 查找学生
    Student findById(Long id);

}
