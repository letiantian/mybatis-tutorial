package mapper;

import bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 根据用户名和密码查询用户。用户名可选
     */
    User findByName(@Param("name") String name, @Param("optionalPassword") String password);

    /**
     * 根据查询条件查询所有符合要求的用户
     */
    List<User> find(User queryCondition);

}