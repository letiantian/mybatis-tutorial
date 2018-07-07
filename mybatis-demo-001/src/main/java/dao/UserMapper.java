package dao;

import bean.User;

public interface UserMapper {

    User findById(int id);

}