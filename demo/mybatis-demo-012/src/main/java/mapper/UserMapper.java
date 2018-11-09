package mapper;

import bean.User;

import java.util.List;

public interface UserMapper {

    User findById(Long id);

    List<User> findByPassword(String password);

}