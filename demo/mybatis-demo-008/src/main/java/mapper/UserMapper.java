package mapper;

import bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 根据密码查询所有用户
     * @param password
     * @return
     */
    List<User> findByPassword(@Param("password") String password, @Param("orderClause") String orderClause);

}