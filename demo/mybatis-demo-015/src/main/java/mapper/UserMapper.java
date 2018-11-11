package mapper;

import bean.User;
import org.apache.ibatis.annotations.*;

import java.util.Map;

public interface UserMapper {

    /**
     * 根据 id 查询用户
     */
    @Select("select * from blog_db.user where id=#{id}")
    User findById(Long id);

    /**
     * 下面的函数都是根据 name 和 password 查询用户
     */

    @Select("select * from blog_db.user where name=#{param1} and password=#{param2}")
    @Results({
                    @Result(id = true, column = "id", property = "id"),
                    @Result(column = "name", property = "name"),
                    @Result(column = "email", property = "email"),
                    @Result(column = "password", property = "password")
            })
    User findByNameAndPasswordV1(String name, String password);

    User findByNameAndPasswordV2(@Param("username") String name, @Param("password") String password);

    @Select("select * from blog_db.user where name=#{username} and password=#{password}")
    User findByNameAndPasswordV3(Map<String,Object> data);

    @Select("select * from blog_db.user where name=#{param1.username} and password=#{param2}")
    User findByNameAndPasswordV4(Map<String,Object> data, String password);

    @Select("select * from blog_db.user where name=#{data.username} and password=#{password}")
    User findByNameAndPasswordV5(@Param("data") Map<String,Object> data, @Param("password") String password);

    @Select("select * from blog_db.user where name=#{name} and password=#{password}")
    User findByNameAndPasswordV6(User user);

    @Select("select * from blog_db.user where name=#{user.name} and password=#{user.password}")
    User findByNameAndPasswordV7(@Param("user") User user);

    /**
     * 插入用户
     * @param user
     */
    @Insert("insert into blog_db.user (name, email, password) VALUES (#{name}, #{email}, #{password})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insertUser(User user);

}