package mapper;

import bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {

    /**
     * 根据 id 查询用户
     */
    User findById(Long id);

    /**
     * 下面的函数都是根据 name 和 password 查询用户
     */
    User findByNameAndPasswordV1(String name, String password);

    User findByNameAndPasswordV2(@Param("username") String name, @Param("password") String password);

    User findByNameAndPasswordV3(Map<String,Object> data);

    User findByNameAndPasswordV4(Map<String,Object> data, String password);

    User findByNameAndPasswordV5(@Param("data") Map<String,Object> data, @Param("password") String password);

    User findByNameAndPasswordV6(User user);

    User findByNameAndPasswordV7(@Param("user") User user);

}