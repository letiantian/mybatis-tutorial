package mapper;

import bean.User;

import java.util.List;

public interface UserMapper {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 根据密码，查询其中一个用户
     * @param password
     * @return
     */
    User findOneUserByPassword(String password); // 必须保证最多返回一条数据，否则会报 TooManyResultsException 错误。无数据，则返回null

    /**
     * 根据密码查询所有用户
     * @param password
     * @return
     */
    List<User> findByPassword(String password);

}