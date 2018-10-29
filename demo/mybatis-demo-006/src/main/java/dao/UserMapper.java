package dao;

import bean.User;

public interface UserMapper {

    User findById(int id);

    int insertUserV1(User user);
    int insertUserV2(User user);

}