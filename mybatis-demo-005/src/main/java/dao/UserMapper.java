package dao;

import bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {

    User findById(int id);

    User findByNameAndPasswordV1(String name, String password);

    User findByNameAndPasswordV2(@Param("username") String name, @Param("password") String password);

    User findByNameAndPasswordV3(Map<String,Object> data);

    User findByNameAndPasswordV4(Map<String,Object> data, String password);

    User findByNameAndPasswordV5(@Param("data") Map<String,Object> data, @Param("password") String password);

    User findByNameAndPasswordV6(User user);

    User findByNameAndPasswordV7(@Param("user") User user);

}