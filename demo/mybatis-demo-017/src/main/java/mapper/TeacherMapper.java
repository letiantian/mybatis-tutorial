package mapper;

import bean.Teacher;

public interface TeacherMapper {

    // 根据 id 查找老师
    Teacher findById(Long id);
}
