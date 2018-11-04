# 06. 如果Bean中成员变量和表中字段命名不一致

本节示例代码在 [mybatis-demo-005](../../demo/mybatis-demo-005) 。

在 [02-查找id为1的用户信息](02-查找id为1的用户信息.md) 中的 User 定义如下：
```java
package bean;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;

}
```
类中的变量名称和数据库中的user表的列名是一致的。

能做到不一致吗？能！

## 修改 User 类

修改为以下内容：
```java
package bean;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String username; // 数据库字段是 name
    private String email;
    private String password;

}
```

User类中的变量name改成了username，这和blog_db数据库中user表中的name字段对应不起来。

此时，若执行下面的代码：
```java
sessionFactory = sqlSessionFactoryBuilder.build(
        Resources.getResourceAsReader("mybatis-config.xml"),
        "development"
);

SqlSession sqlSession = sessionFactory.openSession();

try {
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    User user = userMapper.findById(1L);
    log.info("{}", user);
} finally {
    sqlSession.close();
}
```
结果将是：
```plain
 INFO [main] - User(id=1, username=null, email=letian@111.com, password=123)
```
username 是 null 。我们可以通过修改 UserMapper.xml ，指定user表中的列名和 User 类中的变量名的对应关系。

## 修改 UserMapper.xml 

修改为以下内容：
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC
        "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="mapper.UserMapper">

    <resultMap id="userResult" type="bean.User">
        <result property="username" column="name" javaType="String" jdbcType="VARCHAR"/>
    </resultMap>

    <select id="findById" parameterType="Long" resultMap="userResult" resultType="bean.User">
        select * from blog_db.user where id=#{id}
    </select>

</mapper>
```

在`<resultMap>`中，User类中的username和表中的name对应了起来（建立了映射关系）。`<resultMap>`的id是userResult，`<select>`中也添加了属性resultMap，值为userResult，用来指向上面的`<resultMap>`。

此时，若执行下面的代码：
```java
sessionFactory = sqlSessionFactoryBuilder.build(
        Resources.getResourceAsReader("mybatis-config.xml"),
        "development"
);

SqlSession sqlSession = sessionFactory.openSession();

try {
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    User user = userMapper.findById(1L);
    log.info("{}", user);
} finally {
    sqlSession.close();
}
```
结果将是：
```plain
 INFO [main] - User(id=1, username=letian, email=letian@111.com, password=123)
```