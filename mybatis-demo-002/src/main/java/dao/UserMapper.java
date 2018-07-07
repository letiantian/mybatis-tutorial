package dao;

import bean.User;

import java.util.List;

public interface UserMapper {

    User findById(int id);

    List<User> findByPassword(String password);

    User findOneUserByPassword(String password); // 必须保证最多返回一条数据，否则会报 TooManyResultsException 错误。无数据，则返回null

}