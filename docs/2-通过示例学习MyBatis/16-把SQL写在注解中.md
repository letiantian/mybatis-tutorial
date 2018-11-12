# 16. 把SQL写在注解中



之前我们使用了xml映射文件配置SQL语句。另一个方案是通过注解（Annotation）。还一个方案是XML和注解结合。注解看起来更方便，但在复杂的SQL上表现力不如 XML。



本节示例代码在 [mybatis-demo-015](../../demo/mybatis-demo-015) 。


## 数据准备

见 [01. 数据准备](01-数据准备.md)

## 项目结构

使用 IDEA 创建 gradle 项目，最终结构如下：

<img src="../img/0001.jpg" width=300 />



## 示例



### 在 UserMapper 接口中增加方法，并添加注解

```java
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
```



 `findByNameAndPasswordV2`方法并没有加注解，在 UserMapper.xml 中有映射的SQL配置：

```xml
<select id="findByNameAndPasswordV2" resultType="bean.User">
    select * from blog_db.user where name=#{username} and password=#{password}
</select>
```

注意，对于一个方法基于注解的SQL配置和基于方法的SQL配置，只能存在一个。



在 Main 中编写示例：

```java
@Test
public void test_findById() throws IOException {
    try ( SqlSession sqlSession = getSqlSession() ) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findById(1L);
        log.info("{}", user);
    }
}

private SqlSession getSqlSession() throws IOException {
    SqlSessionFactory sessionFactory;
    sessionFactory = new SqlSessionFactoryBuilder()
            .build(Resources.getResourceAsReader("mybatis-config.xml"));
    return sessionFactory.openSession();
}
```

运行结果：

```plain
 INFO [main] - User(id=1, name=letian, email=letian@111.com, password=123)
```



更多示例见 Main 类代码。